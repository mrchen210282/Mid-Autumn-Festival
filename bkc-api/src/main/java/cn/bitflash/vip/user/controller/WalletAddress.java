package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.entity.UserWalletAddressEntity;
import cn.bitflash.interceptor.ApiLoginInterceptor;
import cn.bitflash.utils.Encrypt;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@RestController
@RequestMapping("/user/walletAddress")
public class WalletAddress {

    //private static final String FILEADDRESS = "D:\\BitFlash-WalletAddress";
    private static final String FILEADDRESS = "/home/statics/walletAddress";
    private static final String PASSWORD = "123456";

    @Autowired
    private UserFeign userFeign;

    /**
     * 生成钱包地址
     *
     * @param uid
     * @return
     * @throws CipherException
     * @throws IOException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public boolean createWalletAddress(String uid) throws CipherException, IOException,
            InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

        UserWalletAddressEntity userWalletAddressEntity = userFeign.selectUserWalletAddressById(uid);
        if (userWalletAddressEntity != null) {
            return false;
        }

        //创建文件夹
        File file = new File(FILEADDRESS);
        //设置为只读
        file.setReadOnly();

        //如果文件夹不存在
        if (!file.exists()) {
            file.mkdir();
        }

        String walletName = WalletUtils.generateNewWalletFile(PASSWORD, new File(FILEADDRESS), false);
        String walleFilePath = FILEADDRESS + "/" + walletName;
        Credentials credentials = WalletUtils.loadCredentials(PASSWORD, walleFilePath);
        String address = credentials.getAddress();
        BigInteger privateKey = credentials.getEcKeyPair().getPrivateKey();

        UserWalletAddressEntity userWalletAddress = new UserWalletAddressEntity();
        userWalletAddress.setUid(uid);
        userWalletAddress.setAddress(Encrypt.SHA256(address+randomUtil()).substring(32));
        userWalletAddress.setPrivateKey(privateKey.toString());
        return userFeign.insetUserWalletAddress(userWalletAddress);
    }

    @Login
    @PostMapping("getAddress")
    public R getAddress(@RequestAttribute(ApiLoginInterceptor.UID) String uid) {
        UserWalletAddressEntity userWalletAddressEntity = userFeign.selectUserWalletAddressById(uid);
        if (userWalletAddressEntity == null) {
            return R.error("钱包不存在！");
        }
        return R.ok().put("userWalletAddressEntity", userWalletAddressEntity);
    }

    /**
     * 验证登录密码是否正确
     */
    @Login
    @PostMapping("verifyPwd")
    public R verifyPwd(@RequestAttribute(ApiLoginInterceptor.UID) String uid, String pwd) {
        UserSecretEntity user = userFeign.selectUserLoginByUid(uid);
        if (!user.getPassword().equals(Encrypt.SHA256(pwd + user.getSalt()))) {
            return R.error("登录密码错误");
        }
        return R.ok();
    }

    /**
     * 生成8位随机数
     *
     * @return
     */
    public static String randomUtil() {

        // 字符源，可以根据需要删减
        String generateSource = "1234567890";// 去掉1和i ，0和o
        String rtnStr = "";
        for (int i = 0; i < 8; i++) {
            // 循环随机获得当次字符，并移走选出的字符
            String nowStr = String
                    .valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr += nowStr;
            generateSource = generateSource.replaceAll(nowStr, "" );
        }
        return rtnStr.toUpperCase();
    }


}

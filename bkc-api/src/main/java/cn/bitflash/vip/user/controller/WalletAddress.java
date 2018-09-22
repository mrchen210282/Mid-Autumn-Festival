package cn.bitflash.vip.user.controller;


import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserWalletAddressEntity;
import cn.bitflash.interceptor.ApiLoginInterceptor;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class WalletAddress {

    private static final String FILEADDRESS = "D:\\BitFlash-WalletAddress";
    private static final String PASSWORD = "123456";

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("walletAddress")
    public R getWalletAddress(@RequestAttribute(ApiLoginInterceptor.UID) String uid)throws CipherException, IOException,
            InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

        //创建文件夹
        File file = new File(FILEADDRESS);

        //设置为只读
        file.setReadOnly();

        //如果文件夹不存在
        if (!file.exists()) {
            file.mkdir();
        }

        String walletName = WalletUtils.generateNewWalletFile(PASSWORD, new File(FILEADDRESS), false);
        String walleFilePath = FILEADDRESS+"/"+walletName;
        Credentials credentials = WalletUtils.loadCredentials(PASSWORD, walleFilePath);
        String address = credentials.getAddress();
        BigInteger privateKey = credentials.getEcKeyPair().getPrivateKey();

        UserWalletAddressEntity userWalletAddress = new UserWalletAddressEntity();
        userWalletAddress.setUid(uid);
        userWalletAddress.setAddress(address);
        userWalletAddress.setPrivateKey(privateKey.toString());

        userFeign.insetUserWalletAddress(userWalletAddress);

        return R.ok().put("userWalletAddress",userWalletAddress);
    }
}

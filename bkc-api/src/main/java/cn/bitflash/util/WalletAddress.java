package cn.bitflash.util;

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

/**
 * @author GAOYGUUO
 */
public class WalletAddress {

    private static final String FILEADDRESS = "D:\\BitFlash-WalletAddress";
    private static final String PASSWORD = "123456";


//    /**
//     * 创建一个钱包文件
//     * walletName：文件名称
//     * password：访问密码
//     */
//    public Map<String , Object> creatAccount(String password)
//            throws CipherException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
//
//        String walletName = WalletUtils.generateNewWalletFile(password, new File(FILEADDRESS), false);
//
//        Map<String , Object> map = new HashMap<String , Object>();
//        map.put("walletName",walletName);
//        map.put("password",password);
//        return map;
//    }

    /**
     * 加载钱包文件
     * 获取：
     * address 钱包地址
     * publicKey 公钥
     * privateKey 私钥
     */
//    public Map<String , Object> loadWallet(String walletName,String password) throws IOException, CipherException {
//
//        String walleFilePath = FILEADDRESS+walletName;
//        Credentials credentials = WalletUtils.loadCredentials(password, walleFilePath);
//        String address = credentials.getAddress();
//        BigInteger publicKey = credentials.getEcKeyPair().getPublicKey();
//        BigInteger privateKey = credentials.getEcKeyPair().getPrivateKey();
//
//        Map<String , Object> map = new HashMap<String , Object>();
//        map.put("address",address);
//        map.put("publicKey",publicKey);
//        map.put("privateKey",privateKey);
//
//        return map;
//    }

    /**
     * 本地创建文件夹: BitFlash-WalletAddress
     */
    private static void createFile(){
        //创建文件夹
        File file = new File(FILEADDRESS);
        //设置为只读
        file.setReadOnly();
        //如果文件夹不存在
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 生成私钥，公钥，钱包地址
     */
    public static Map<String , Object> getWalletAddress()
            throws CipherException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        createFile();
        String walletName = WalletUtils.generateNewWalletFile(PASSWORD, new File(FILEADDRESS), false);
        String walleFilePath = FILEADDRESS+"/"+walletName;
        Credentials credentials = WalletUtils.loadCredentials(PASSWORD, walleFilePath);
        String address = credentials.getAddress();
        BigInteger publicKey = credentials.getEcKeyPair().getPublicKey();
        BigInteger privateKey = credentials.getEcKeyPair().getPrivateKey();
        Map<String , Object> map = new HashMap<String , Object>();
        map.put("address",address);
        map.put("publicKey",publicKey);
        map.put("privateKey",privateKey);
        return map;
    }

}

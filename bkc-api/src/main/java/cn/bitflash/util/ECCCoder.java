package cn.bitflash.util;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.*;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.NullCipher;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.ECPointUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.ec.ECKeyFactory;
import sun.security.ec.ECPrivateKeyImpl;
import sun.security.ec.ECPublicKeyImpl;

public class ECCCoder {

    public static final String ALGORITHM = "EC";
    private static final String PUBLIC_KEY = "ECCPublicKey";
    private static final String PRIVATE_KEY = "ECCPrivateKey";


//    /**
//     * 解密
//     * 用私钥解密
//     *     
//     */
//    public static byte[] decrypt(byte[] data, String key) throws Exception {
//    // 对密钥解密
//        byte[] keyBytes = decryptBASE64(key);
//
//    // 取得私钥
//        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = ECKeyFactory.INSTANCE;
//        ECPrivateKey priKey = (ECPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
//
//        ECPrivateKeySpec ecPrivateKeySpec = new ECPrivateKeySpec(priKey.getS(), priKey.getParams());
//    // 对数据解密
//    // TODO Chipher不支持EC算法 未能实现
//        Cipher cipher = new NullCipher();
//    // Cipher.getInstance(ALGORITHM, keyFactory.getProvider());
//        cipher.init(Cipher.DECRYPT_MODE, priKey, ecPrivateKeySpec.getParams());
//        return cipher.doFinal(data);
//    }

//    /**
//     * 加密
//     * 用公钥加密
//     *     
//     */
//    public static byte[] encrypt(byte[] data, String privateKey) throws Exception {
//        // 对公钥解密
//        byte[] keyBytes = decryptBASE64(privateKey);
//
//        // 取得公钥
//        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = ECKeyFactory.INSTANCE;
//        ECPublicKey pubKey = (ECPublicKey) keyFactory.generatePublic(x509KeySpec);
//        ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(pubKey.getW(), pubKey.getParams());
//
//        // 对数据加密
//        // TODO Chipher不支持EC算法 未能实现
//        Cipher cipher = new NullCipher();
//        // Cipher.getInstance(ALGORITHM, keyFactory.getProvider());
//        cipher.init(Cipher.ENCRYPT_MODE, pubKey, ecPublicKeySpec.getParams());
//        return cipher.doFinal(data);
//    }


    /**
     * 初始化密钥
     *     
     */
    public static Map<String, Object> initKey() throws Exception {
        BigInteger x1 = new BigInteger("2fe13c0537bbc11acaa07d793de4e6d5e5c94eee8", 16);
        BigInteger x2 = new BigInteger("289070fb05d38ff58321f2e800536d538ccdaa3d9", 16);
        ECPoint g = new ECPoint(x1, x2);
        BigInteger n = new BigInteger("5846006549323611672814741753598448348329118574063", 10);
        int h = 2;
        int m = 163;
        int[] ks = {7, 6, 3};
        ECFieldF2m ecField = new ECFieldF2m(m, ks);
        // y^2+xy=x^3+x^2+1
        BigInteger a = new BigInteger("1", 2);
        BigInteger b = new BigInteger("1", 2);

        EllipticCurve ellipticCurve = new EllipticCurve(ecField, a, b);

        ECParameterSpec ecParameterSpec = new ECParameterSpec(ellipticCurve, g, n, h);
        // 公钥
        ECPublicKey publicKey = new ECPublicKeyImpl(g, ecParameterSpec);
        BigInteger s = new BigInteger("1234006549323611672814741753598448348329118574063", 10);
        // 私钥
        ECPrivateKey privateKey = new ECPrivateKeyImpl(s, ecParameterSpec);


        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 取得私钥
     *     
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *     
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * BASE64加密
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static void main(String[] args) {
        try {
            Map<String, Object> keyMap = initKey();
                String publicKey = ECCCoder.getPublicKey(keyMap);
                String privateKey = ECCCoder.getPrivateKey(keyMap);
                System.err.println("公钥: \n" + publicKey);
                System.err.println("私钥： \n" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

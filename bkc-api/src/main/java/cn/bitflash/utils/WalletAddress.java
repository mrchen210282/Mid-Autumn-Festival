package cn.bitflash.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Map;

public class WalletAddress {
    /**
     * 1.随机选取一个64位、作为私钥。
     *   例：18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725
     *
     * 2.使用椭圆曲线加密算法（ECDSA-secp256k1）计算私钥所对应的非压缩公钥
     *   例： 0450863AD64A87AE8A2FE83C1AF1A8403CB53F53E486D8511DAD8A04887E5B
     *       23522CD470243453A299FA9E77237716103ABC11A1DF38855ED6F2EE187E9C582BA6
     *
     * 3.计算公钥的 SHA-256 哈希值
     *   例： 010966776006953D5567439E5E39F86A0D273BEE
     *
     * 4.前面加入版本号0x00
     *   例： 00010966776006953D5567439E5E39F86A0D273BEE
     *
     * 5.取上一步结果，计算 SHA-256 哈希值
     *   例： D61967F63C7DD183914A4AE452C9F6AD5D462CE3D277798075B107615C1A8A30
     *
     * 6.取上一步结果的前4个字节（8位十六进制）
     *   例： D61967F6
     *
     * 7.把这4个字节加在第4步的结果后面，作为校验
     *   例：00010966776006953D5567439E5E39F86A0D273BEED61967F6
     *
     * 8.用base58表示法变换一下地址
     *   例：16UwLL9Risc3QfPqBUvKofHmBQ7wMtjvM
     */


    //------------------------------------SHA256-------------------------------------
    /**
     * 利用java原生的类实现SHA256加密
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256(String str){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }
    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
//------------------------------------FINISH-------------------------------------


    private static final String PUBLIC_KEY = "ECCPublicKey";
    private static final String PRIVATE_KEY = "ECCPrivateKey";

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


    public static void main(String[] args){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            // curveName这里取值：secp256k1
            ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256k1");
            keyPairGenerator.initialize(ecGenParameterSpec, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            // 获取公钥
            PublicKey publicKey = keyPair.getPublic();
            // 获取私钥
            PrivateKey privateKey = keyPair.getPrivate();
            String k = encryptBASE64(publicKey.getEncoded());
            System.out.println(privateKey);
            System.out.println("加密"+k);
            System.out.println("解密"+decryptBASE64(k));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

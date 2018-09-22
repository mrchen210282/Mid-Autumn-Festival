package cn.bitflash.utils;

public class RandomNumUtil {

    public static String nBit(int length) {
        // 字符源，可以根据需要删减
        String generateSource = "1234567890";
        String rtnStr = "";
        for (int i = 0; i < length; i++) {
            // 循环随机获得当次字符，并移走选出的字符
            String nowStr = String
                    .valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr += nowStr;
            generateSource = generateSource.replaceAll(nowStr, "" );
        }
        return rtnStr;
    }
}

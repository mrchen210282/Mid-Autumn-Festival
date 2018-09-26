package cn.bitflash.vip.user.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: noah
 * Date: 8/2/13
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class Base64Coder {
    public final static char[] base64_alphabet = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S'
            , 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'
            , 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4'
            , '5', '6', '7', '8', '9', '+', '/', '='
    };

    public static String encode(String content) {

        byte[] data = content.getBytes();
        int length = data.length;
        byte[] char_array_3 = new byte[]{0, 0, 0};
        byte[] char_array_4 = new byte[]{'=', '=', '=', '='};
        String retContent = "";
        int i = 0;
        int j = 0;
        int reversePos = 0;
        while (length > 0) {
            length--;
            char_array_3[i++] = data[reversePos++];
            if (i == 3) {
                char_array_4[0] = (byte) ((char_array_3[0] & 0xfc) >> 2); // convert the char
                char_array_4[1] = (byte) (((char_array_3[0] & 0x03) << 4) + ((char_array_3[1] & 0xf0) >> 4));
                char_array_4[2] = (byte) (((char_array_3[1] & 0x0f) << 2) + ((char_array_3[2] & 0xc0) >> 6));
                char_array_4[3] = (byte) (char_array_3[2] & 0x3f);
                for (i = 0; (i < 4); i++)
                    retContent += base64_alphabet[char_array_4[i]];
                i = 0;
            }
        }

        // handling the last input content
        if (i > 0) {
            for (j = i; j < 3; j++)
                char_array_3[j] = 0; // padding of zero

            char_array_4[0] = (byte) ((char_array_3[0] & 0xfc) >> 2); // right shift
            char_array_4[1] = (byte) (((char_array_3[0] & 0x03) << 4) + ((char_array_3[1] & 0xf0) >> 4));
            char_array_4[2] = (byte) (((char_array_3[1] & 0x0f) << 2) + ((char_array_3[2] & 0xc0) >> 6));
            char_array_4[3] = (byte) (char_array_3[2] & 0x3f);

            for (j = 0; (j < i + 1); j++)
                retContent += base64_alphabet[char_array_4[j]];

            while ((i++ < 3)) // padding of '=' of output string
                retContent += '=';

        }
        return retContent;
    }

    public static String decode(String enContent) {
        byte[] data = enContent.getBytes();
        int i = 0, j = 0, enCode = 0;
        int mLength = data.length;
        byte[] char_array_4 = new byte[4];
        byte[] char_array_3 = new byte[3];
        String retContent = "";

        // filter out the padding '=' chars
        while (mLength > 0 && (((char) data[enCode]) != '=') && isBase64((char) data[enCode])) {
            mLength--;
            char_array_4[i++] = data[enCode++];
            if (i == 4) {
                for (i = 0; i < 4; i++)
                    char_array_4[i] = findChar((char) char_array_4[i]);

                char_array_3[0] = (byte) ((char_array_4[0] << 2) + ((char_array_4[1] & 0x30) >> 4));
                char_array_3[1] = (byte) (((char_array_4[1] & 0xf) << 4) + ((char_array_4[2] & 0x3c) >> 2));
                char_array_3[2] = (byte) (((char_array_4[2] & 0x3) << 6) + char_array_4[3]);

                for (i = 0; (i < 3); i++)
                    retContent += (char) char_array_3[i];
                i = 0;
            }
        }

        // last content handling
        if (i > 0) {
            for (j = i; j < 4; j++)
                char_array_4[j] = 0;

            for (j = 0; j < 4; j++)
                char_array_4[j] = findChar((char) char_array_4[j]);

            char_array_3[0] = (byte) ((char_array_4[0] << 2) + ((char_array_4[1] & 0x30) >> 4));
            char_array_3[1] = (byte) (((char_array_4[1] & 0xf) << 4) + ((char_array_4[2] & 0x3c) >> 2));
            char_array_3[2] = (byte) (((char_array_4[2] & 0x3) << 6) + char_array_4[3]);

            for (j = 0; (j < i - 1); j++)
                retContent += (char) char_array_3[j];
        }

        return retContent;
    }

    public static boolean isBase64(char c) {
        boolean base64 = false;
        for (int i = 0; i < 32; i++) {
            if (c == base64_alphabet[i]) {
                base64 = true;
                break;
            }
        }
        return base64;
    }

    public static byte findChar(char x) {
        byte index = 32; // 65th char '='
        for (int i = 0; i < 32; i++) {
            if (x == base64_alphabet[i]) {
                index = (byte) i;
                break;
            }
        }
        return index;
    }

    /**
     * <p> test data and result should like below output , RFC4648 Sample </p>
     * BASE64("") = ""
     * BASE64("f") = "Zg=="
     * BASE64("fo") = "Zm8="
     * BASE64("foo") = "Zm9v"
     * BASE64("foob") = "Zm9vYg=="
     * BASE64("fooba") = "Zm9vYmE="
     * BASE64("foobar") = "Zm9vYmFy"
     *
     * @param args
     */
    public static void main(String[] args) {
        // BASE64Encoder coder = new BASE64Encoder();
        // System.out.println(coder.encode("foobar".getBytes()));

        System.out.println("#--------------encode---------------#");
        System.out.println(encode(""));
        System.out.println(encode("f"));
        System.out.println(encode("fo"));
        System.out.println(encode("foo"));
        System.out.println(encode("foob"));
        System.out.println(encode("fooba"));
        System.out.println(encode("foobar"));
        System.out.println(encode("030ba1ba3b8d8f7bd4a70828ec0e749dd26ee4cdd18d058c880afa121fad60e5b6"));
        System.out.println("#--------------decode---------------#");
        System.out.println(decode(""));
        System.out.println(decode("Zg=="));
        System.out.println(decode("Zm8="));
        System.out.println(decode("Zm9v"));
        System.out.println(decode("Zm9vYg=="));
        System.out.println(decode("Zm9vYmE="));
        System.out.println(decode("Zm9vYmFy"));
        System.out.println(decode("MTIzNDU2Nzg5c1Mk"));

    }

}

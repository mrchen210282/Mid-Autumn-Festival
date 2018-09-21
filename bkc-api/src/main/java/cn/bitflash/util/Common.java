package cn.bitflash.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

/**
 * @author wangjun
 */
public class Common {

	// 连接地址
	public final static String ADDRESS = "address";

	public final static String TOKEN = "token";

	//userinfo 实名认证完成状态码
	public final static String AUTHENTICATION = "2";


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

	public static void main(String[] arg) {
		System.out.println(Common.randomUtil());
	}


}

package cn.bitflash.vip.authorization.common;

import cn.bitflash.utils.ExternalMD5;

import java.util.List;

/**
 * wangjun
 * 2018.10.17
 */
public class Common {
    public static String returnMD5(List<Object> list) {
        if (list.size() > 0) {
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                buf.append(list.get(i));
            }
            String returnSign = new ExternalMD5(buf.toString()).asHex();
            return returnSign;
        } else {
            return null;
        }
    }
}

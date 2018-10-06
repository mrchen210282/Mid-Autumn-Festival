package cn.bitflash.vip.trade.feign;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalUtils {
    public static String DecimalFormat(BigDecimal big) {
        String avaliable = "";
        if (null != big) {
            DecimalFormat df = new DecimalFormat("#.000");
            Double availableAssets = Double.valueOf(big.toString());
            avaliable = df.format(availableAssets);
        }
        return avaliable;
    }
}

package cn.bitflash.vip.trade.feign;

import java.text.DecimalFormat;

public class TradeCommon {
    // 最小价格
    public final static String MIN_PRICE = "0.33";

    public final static Double MIN_NUMBER = 100d;

    // 最小价格
    public final static Double MULTIPLE = 10d;

    // 超时时间(毫秒)
    public final static String OUT_TIME = "outTime";

    // 连接地址
    public final static String ADDRESS = "address";

    // vip等级0
    public final static String VIP_LEVEL_0 = "0";

    // 锁定订单次数
    public final static String LOCK_TRADE = "lock_trade";

    // 卖出
    public final static String STATE_SELL = "1";

    // 撤消
    public final static String STATE_CANCEL = "3";

    // 已完成
    public final static String STATE_PAY = "4";

    // 已锁定
    public final static String STATE_LOCK = "5";

    // 待确认
    public final static String STATE_CONFIRM = "6";

    // 申拆中
    public final static String STATE_COMPLAINT = "9";


    // 发送信息标题内容值
    public final static String MSG_TEXT = "paymoney";

    public final static double poundage = 0.025;

    /**
     *
     * 保留两个小数，并不进行四舍五入
     * 100.00 > 100.00
     * 100.10 > 100.10
     * 100.01 > 100.01
     * @return
     */
    public static String decimalFormat ( double d){
        if (d > 0) {
            DecimalFormat df = new DecimalFormat("######0.00");
            String str = df.format(d);
            return str;
        } else {
            return null;
        }
    }
}

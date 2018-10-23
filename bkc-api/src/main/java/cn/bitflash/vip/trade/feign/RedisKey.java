package cn.bitflash.vip.trade.feign;

public class RedisKey {

    // redis统计锁定订单数量
    public final static String COUNT_LOCK = "countLock_"; //+uid

    public final static String BUY_COUNT_LOCK = "buyLock_"; //+uid

    //redis锁定订单key值
    public final static String LOCK_TRADE = "uid";

    // redis缓存催单订单号key值
    public final static String SEND_TRADE_MESSAGE = "sendTrade_";

}
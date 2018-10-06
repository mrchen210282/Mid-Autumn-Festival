package cn.bitflash.vip.buy.controller;

public class BuyCommon {

    //订单撤销
    public final static String ORDER_STATE_CANCEL = "0";
    //订单取消
    public final static String ORDER_STATE_PUBLISH = "1";
    //待付款 -- 待收款
    public final static String ORDER_STATE_STEP1 = "2";
    //待收币 -- 待确认
    public final static String ORDER_STATE_STEP2 = "3";
    //订单完成
    public final static String ORDER_STATE_FINISH = "6";
    //订单申诉
    public final static String ORDER_STATE_APPEAL = "9";

    //----------------code值---------------------

    public final static String SUCCESS = "0";
    //交易失败
    public final static String FAIL = "1";
    //交易不存在
    public final static String TRADEMISS = "2";

    // redis缓存订单号key
    public final static String ADD_LOCKNUM = "userBuy_";
}

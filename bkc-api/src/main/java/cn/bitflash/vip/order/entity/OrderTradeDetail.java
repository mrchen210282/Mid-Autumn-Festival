package cn.bitflash.vip.order.entity;

import java.math.BigDecimal;

public class OrderTradeDetail {

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 价钱
     */
    private String acprice;

    /**
     * 发单人信息
     */
    private String purUid;

    /**
     * 应单人信息
     */
    private String conUid;

    private String tradeState;

    /**
     * 数量
     */
    private String acquantity;

    private String purName;

    private String purMobile;

    private String conName;

    private String conMobile;

    //交易总额
    private BigDecimal tradeAmount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAcprice() {
        return acprice;
    }

    public void setAcprice(String acprice) {
        this.acprice = acprice;
    }

    public String getPurUid() {
        return purUid;
    }

    public void setPurUid(String purUid) {
        this.purUid = purUid;
    }

    public String getConUid() {
        return conUid;
    }

    public void setConUid(String conUid) {
        this.conUid = conUid;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getAcquantity() {
        return acquantity;
    }

    public void setAcquantity(String acquantity) {
        this.acquantity = acquantity;
    }

    public String getPurName() {
        return purName;
    }

    public void setPurName(String purName) {
        this.purName = purName;
    }

    public String getPurMobile() {
        return purMobile;
    }

    public void setPurMobile(String purMobile) {
        this.purMobile = purMobile;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getConMobile() {
        return conMobile;
    }

    public void setConMobile(String conMobile) {
        this.conMobile = conMobile;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }
}

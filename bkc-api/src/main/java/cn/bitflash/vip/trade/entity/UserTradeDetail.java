package cn.bitflash.vip.trade.entity;

import java.math.BigDecimal;

public class UserTradeDetail {

    private BigDecimal price;

    /**
     * 卖出数量
     */
    private BigDecimal quantity;

    //手续费
    private BigDecimal poundage;

    //交易总额
    private BigDecimal tradeAmount;

    //扣除交易额=交易额+手续费
    private BigDecimal deductAmount;

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getDeductAmount() {
        return deductAmount;
    }

    public void setDeductAmount(BigDecimal deductAmount) {
        this.deductAmount = deductAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }
}

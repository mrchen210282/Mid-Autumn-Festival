package cn.bitflash.entity;

import java.math.BigDecimal;

public class UserCashAssetsJoinDictComputingPowerBean {

    private String uid;

    private BigDecimal purchase;

    private BigDecimal totleIncome;

    private BigDecimal differenceIncome;

    private BigDecimal sharedIncome;

    private BigDecimal lotto;

    private Integer level;

    //算力
    private BigDecimal rate;

    //可提现
    private BigDecimal withdrawCash;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getPurchase() {
        return purchase;
    }

    public void setPurchase(BigDecimal purchase) {
        this.purchase = purchase;
    }

    public BigDecimal getTotleIncome() {
        return totleIncome;
    }

    public void setTotleIncome(BigDecimal totleIncome) {
        this.totleIncome = totleIncome;
    }

    public BigDecimal getDifferenceIncome() {
        return differenceIncome;
    }

    public void setDifferenceIncome(BigDecimal differenceIncome) {
        this.differenceIncome = differenceIncome;
    }

    public BigDecimal getSharedIncome() {
        return sharedIncome;
    }

    public void setSharedIncome(BigDecimal sharedIncome) {
        this.sharedIncome = sharedIncome;
    }

    public BigDecimal getLotto() {
        return lotto;
    }

    public void setLotto(BigDecimal lotto) {
        this.lotto = lotto;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getWithdrawCash() {
        return withdrawCash;
    }

    public void setWithdrawCash(BigDecimal withdrawCash) {
        this.withdrawCash = withdrawCash;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}

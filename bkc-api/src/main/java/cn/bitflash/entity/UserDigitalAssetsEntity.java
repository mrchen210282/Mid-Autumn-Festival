package cn.bitflash.entity;

import java.math.BigDecimal;

public class UserDigitalAssetsEntity {

    private String uid;

    private BigDecimal purchase;

    private BigDecimal frozen;

    private BigDecimal totelRelease;

    private BigDecimal available;

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

    public BigDecimal getFrozen() {
        return frozen;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }

    public BigDecimal getTotelRelease() {
        return totelRelease;
    }

    public void setTotelRelease(BigDecimal totelRelease) {
        this.totelRelease = totelRelease;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }
}

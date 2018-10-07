package cn.bitflash.entity;

public class UserBrokerageEntity {

    private String id;
    private Float purchaseBrokerage;
    private Float sellBrokerage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getPurchaseBrokerage() {
        return purchaseBrokerage;
    }

    public void setPurchaseBrokerage(Float purchaseBrokerage) {
        this.purchaseBrokerage = purchaseBrokerage;
    }

    public Float getSellBrokerage() {
        return sellBrokerage;
    }

    public void setSellBrokerage(Float sellBrokerage) {
        this.sellBrokerage = sellBrokerage;
    }
}

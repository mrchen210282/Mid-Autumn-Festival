package cn.bitflash.entity;

import java.math.BigDecimal;

public class UserAssetsNpcEntity {

    private String uid;

    //总购买
    private BigDecimal purchase;

    //总收益
    private BigDecimal totelAssets;

    //冻结
    private BigDecimal frozenAssets;

    //每天释放
    private BigDecimal dailyRelease;

    //总释放量
    private BigDecimal totelRelease;

    //npc可用资产
    private BigDecimal availableAssets;

    //npc购买价格
    private BigDecimal npcPrice;

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

    public BigDecimal getTotelAssets() {
        return totelAssets;
    }

    public void setTotelAssets(BigDecimal totelAssets) {
        this.totelAssets = totelAssets;
    }

    public BigDecimal getFrozenAssets() {
        return frozenAssets;
    }

    public void setFrozenAssets(BigDecimal frozenAssets) {
        this.frozenAssets = frozenAssets;
    }

    public BigDecimal getDailyRelease() {
        return dailyRelease;
    }

    public void setDailyRelease(BigDecimal dailyRelease) {
        this.dailyRelease = dailyRelease;
    }

    public BigDecimal getTotelRelease() {
        return totelRelease;
    }

    public void setTotelRelease(BigDecimal totelRelease) {
        this.totelRelease = totelRelease;
    }

    public BigDecimal getAvailableAssets() {
        return availableAssets;
    }

    public void setAvailableAssets(BigDecimal availableAssets) {
        this.availableAssets = availableAssets;
    }

    public BigDecimal getNpcPrice() {
        return npcPrice;
    }

    public void setNpcPrice(BigDecimal npcPrice) {
        this.npcPrice = npcPrice;
    }
}

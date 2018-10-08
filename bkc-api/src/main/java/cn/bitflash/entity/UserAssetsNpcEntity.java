package cn.bitflash.entity;

public class UserAssetsNpcEntity {

    private String uid;

    //总购买
    private float purchase;

    //总收益
    private float totelAssets;

    //冻结
    private float frozenAssets;

    //每天释放
    private float dailyRelease;

    //总释放量
    private float totelRelease;

    //npc可用资产
    private float availableAssets;

    //npc购买价格
    private float npcPrice;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public float getPurchase() {
        return purchase;
    }

    public void setPurchase(float purchase) {
        this.purchase = purchase;
    }

    public float getTotelAssets() {
        return totelAssets;
    }

    public void setTotelAssets(float totelAssets) {
        this.totelAssets = totelAssets;
    }

    public float getFrozenAssets() {
        return frozenAssets;
    }

    public void setFrozenAssets(float frozenAssets) {
        this.frozenAssets = frozenAssets;
    }

    public float getDailyRelease() {
        return dailyRelease;
    }

    public void setDailyRelease(float dailyRelease) {
        this.dailyRelease = dailyRelease;
    }

    public float getTotelRelease() {
        return totelRelease;
    }

    public void setTotelRelease(float totelRelease) {
        this.totelRelease = totelRelease;
    }

    public float getAvailableAssets() {
        return availableAssets;
    }

    public void setAvailableAssets(float availableAssets) {
        this.availableAssets = availableAssets;
    }

    public float getNpcPrice() {
        return npcPrice;
    }

    public void setNpcPrice(float npcPrice) {
        this.npcPrice = npcPrice;
    }
}

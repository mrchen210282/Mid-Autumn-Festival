package cn.bitflash.entity;

public class UserAssetsHlbEntity {

    private String uid;

    //总收益
    private float totelAssets;

    //冻结
    private float frozenAssets;

    //每天释放
    private float dailyRelease;

    //总释放量
    private float totelRelease;

    //hlb可用资产
    private float availableAssets;

    //左业绩
    private float lftAchievement;

    //右业绩
    private float rgtAchievement;

    //当前vip等级释放量
    private float vipReleaseCash;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public float getLftAchievement() {
        return lftAchievement;
    }

    public void setLftAchievement(float lftAchievement) {
        this.lftAchievement = lftAchievement;
    }

    public float getRgtAchievement() {
        return rgtAchievement;
    }

    public void setRgtAchievement(float rgtAchievement) {
        this.rgtAchievement = rgtAchievement;
    }

    public float getVipReleaseCash() {
        return vipReleaseCash;
    }

    public void setVipReleaseCash(float vipReleaseCash) {
        this.vipReleaseCash = vipReleaseCash;
    }
}

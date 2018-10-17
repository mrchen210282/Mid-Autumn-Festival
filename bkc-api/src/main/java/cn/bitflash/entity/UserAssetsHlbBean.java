package cn.bitflash.entity;

import java.math.BigDecimal;

public class UserAssetsHlbBean {

    private String uid;

    //总收益
    private float totelAssets;

    //冻结
    private BigDecimal frozenAssets;

    //每天释放
    private float dailyRelease;

    //总释放量
    private float totelRelease;

    //hlb可用资产
    private BigDecimal availableAssets;

    //左业绩
    private float lftAchievement;

    //右业绩
    private float rgtAchievement;

    //当前vip等级释放量
    private float vipReleaseCash;

    private String power;

    //档位
    private float hlbAmount;

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

    public BigDecimal getFrozenAssets() {
        return frozenAssets;
    }

    public void setFrozenAssets(BigDecimal frozenAssets) {
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

    public BigDecimal getHlbAssets() {
        return availableAssets;
    }

    public void setHlbAssets(BigDecimal hlbAssets) {
        this.availableAssets = hlbAssets;
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

    public BigDecimal getAvailableAssets() {
        return availableAssets;
    }

    public void setAvailableAssets(BigDecimal availableAssets) {
        this.availableAssets = availableAssets;
    }

    public float getVipReleaseCash() {
        return vipReleaseCash;
    }

    public void setVipReleaseCash(float vipReleaseCash) {
        this.vipReleaseCash = vipReleaseCash;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public float getHlbAmount() {
        return hlbAmount;
    }

    public void setHlbAmount(float hlbAmount) {
        this.hlbAmount = hlbAmount;
    }
}

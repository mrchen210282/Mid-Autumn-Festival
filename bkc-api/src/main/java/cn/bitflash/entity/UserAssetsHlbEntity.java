package cn.bitflash.entity;

import java.math.BigDecimal;

public class UserAssetsHlbEntity {

    private String uid;

    //总收益
    private BigDecimal totelAssets;

    //冻结
    private BigDecimal frozenAssets;

    //每天释放
    private BigDecimal dailyRelease;

    //总释放量
    private BigDecimal totelRelease;

    //hlb可用资产
    private BigDecimal availableAssets;

    //左业绩
    private BigDecimal lftAchievement;

    //右业绩
    private BigDecimal rgtAchievement;

    //当前vip等级释放量
    private BigDecimal regulateRelease;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public BigDecimal getLftAchievement() {
        return lftAchievement;
    }

    public void setLftAchievement(BigDecimal lftAchievement) {
        this.lftAchievement = lftAchievement;
    }

    public BigDecimal getRgtAchievement() {
        return rgtAchievement;
    }

    public void setRgtAchievement(BigDecimal rgtAchievement) {
        this.rgtAchievement = rgtAchievement;
    }

    public BigDecimal getRegulateRelease() {
        return regulateRelease;
    }

    public void setRegulateRelease(BigDecimal regulateRelease) {
        this.regulateRelease = regulateRelease;
    }
}

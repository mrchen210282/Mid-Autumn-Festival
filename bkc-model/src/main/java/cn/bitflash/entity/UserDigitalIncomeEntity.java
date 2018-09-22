package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("user_digital_income")
public class UserDigitalIncomeEntity implements Serializable {
    @TableId
    private String uid;

    private BigDecimal purchase;

    private BigDecimal frozenAssets;

    private BigDecimal purchaseRelease;

    private BigDecimal dailyRelease;

    private BigDecimal totelRelease;

    private BigDecimal availableAssets;

    private BigDecimal lftAchievement;

    private BigDecimal rgtAchievement;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

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

    public BigDecimal getFrozenAssets() {
        return frozenAssets;
    }

    public void setFrozenAssets(BigDecimal frozenAssets) {
        this.frozenAssets = frozenAssets;
    }

    public BigDecimal getPurchaseRelease() {
        return purchaseRelease;
    }

    public void setPurchaseRelease(BigDecimal purchaseRelease) {
        this.purchaseRelease = purchaseRelease;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

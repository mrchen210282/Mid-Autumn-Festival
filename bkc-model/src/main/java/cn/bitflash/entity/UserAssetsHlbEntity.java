package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("user_assets_hlb")
public class UserAssetsHlbEntity {

    @TableId(type = IdType.INPUT)
    private String uid;

    //总收益
    private Float totelAssets;

    //冻结
    private Float frozenAssets;

    //每天释放
    private Float dailyRelease;

    //总释放量
    private Float totelRelease;

    //hlb可用资产
    private Float availableAssets;

    //左业绩
    private Float lftAchievement;

    //右业绩
    private Float rgtAchievement;

    //当前vip等级释放量
    private Float regulateRelease;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Float getTotelAssets() {
        return totelAssets;
    }

    public void setTotelAssets(Float totelAssets) {
        this.totelAssets = totelAssets;
    }

    public Float getFrozenAssets() {
        return frozenAssets;
    }

    public void setFrozenAssets(Float frozenAssets) {
        this.frozenAssets = frozenAssets;
    }

    public Float getDailyRelease() {
        return dailyRelease;
    }

    public void setDailyRelease(Float dailyRelease) {
        this.dailyRelease = dailyRelease;
    }

    public Float getTotelRelease() {
        return totelRelease;
    }

    public void setTotelRelease(Float totelRelease) {
        this.totelRelease = totelRelease;
    }

    public Float getAvailableAssets() {
        return availableAssets;
    }

    public void setAvailableAssets(Float availableAssets) {
        this.availableAssets = availableAssets;
    }

    public Float getLftAchievement() {
        return lftAchievement;
    }

    public void setLftAchievement(Float lftAchievement) {
        this.lftAchievement = lftAchievement;
    }

    public Float getRgtAchievement() {
        return rgtAchievement;
    }

    public void setRgtAchievement(Float rgtAchievement) {
        this.rgtAchievement = rgtAchievement;
    }

    public Float getRegulateRelease() {
        return regulateRelease;
    }

    public void setRegulateRelease(Float regulateRelease) {
        this.regulateRelease = regulateRelease;
    }
}

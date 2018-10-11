package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("user_assets_npc")
public class UserAssetsNpcEntity {

    @TableId(type = IdType.INPUT)
    private String uid;

    //总购买
    private Float purchase;

    //总收益
    private Float totelAssets;

    //冻结
    private Float frozenAssets;

    //每天释放
    private Float dailyRelease;

    //总释放量
    private Float totelRelease;

    //npc可用资产
    private Float availableAssets;

    //npc购买价格
    private Float npcPrice;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Float getPurchase() {
        return purchase;
    }

    public void setPurchase(Float purchase) {
        this.purchase = purchase;
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

    public Float getNpcPrice() {
        return npcPrice;
    }

    public void setNpcPrice(Float npcPrice) {
        this.npcPrice = npcPrice;
    }
}

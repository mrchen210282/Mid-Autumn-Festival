package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

@TableName("user_info_config")
public class UserInfoConfigEntity implements Serializable {

    private static final long serialVersionUID = 7629853906064099813L;
    @TableId(type = IdType.INPUT)
    private Integer id;

    private Integer min;

    private float power;

    private String showPower;

    private float releaseProfit;

    private Double giveRate;

    private String showGiveRate;

    private float maxAchievement;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public String getShowPower() {
        return showPower;
    }

    public void setShowPower(String showPower) {
        this.showPower = showPower;
    }

    public float getReleaseProfit() {
        return releaseProfit;
    }

    public void setReleaseProfit(float releaseProfit) {
        this.releaseProfit = releaseProfit;
    }

    public Double getGiveRate() {
        return giveRate;
    }

    public void setGiveRate(Double giveRate) {
        this.giveRate = giveRate;
    }

    public String getShowGiveRate() {
        return showGiveRate;
    }

    public void setShowGiveRate(String showGiveRate) {
        this.showGiveRate = showGiveRate;
    }

    public float getMaxAchievement() {
        return maxAchievement;
    }

    public void setMaxAchievement(float maxAchievement) {
        this.maxAchievement = maxAchievement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("system_power")
public class SystemPowerEntity {
    @TableId
    private Integer id;

    private Float power;

    private Integer upgradeNum;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
    }

    public Integer getUpgradeNum() {
        return upgradeNum;
    }

    public void setUpgradeNum(Integer upgradeNum) {
        this.upgradeNum = upgradeNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

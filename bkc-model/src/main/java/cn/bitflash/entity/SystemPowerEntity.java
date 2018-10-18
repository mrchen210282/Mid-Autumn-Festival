package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("system_power")
public class SystemPowerEntity {

    private Float power;

    @TableId
    private Integer upgradeNum;

    private String remark;

    private Float specialPower;

    public Float getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(Float specialPower) {
        this.specialPower = specialPower;
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

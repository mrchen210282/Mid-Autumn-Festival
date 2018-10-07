package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("system_power")
public class SystemPowerEntity {
    @TableId
    private int id;

    private float power;

    private int  upgradeNum;

    private int  degradeNum;

    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public int getUpgradeNum() {
        return upgradeNum;
    }

    public void setUpgradeNum(int upgradeNum) {
        this.upgradeNum = upgradeNum;
    }

    public int getDegradeNum() {
        return degradeNum;
    }

    public void setDegradeNum(int degradeNum) {
        this.degradeNum = degradeNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

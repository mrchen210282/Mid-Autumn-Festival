package cn.bitflash.entity;

import java.io.Serializable;

public class VipConditionsEntity implements Serializable {

    private String level;

    private float power;

    private int bkcount;

    private String remark;

    private int state;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public int getBkcount() {
        return bkcount;
    }

    public void setBkcount(int bkcount) {
        this.bkcount = bkcount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

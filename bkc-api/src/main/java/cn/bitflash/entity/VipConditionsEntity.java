package cn.bitflash.entity;

import java.io.Serializable;

public class VipConditionsEntity implements Serializable {

    private String level;

    private float power;

    private int bkcountremarkstate;

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

    public int getBkcountremarkstate() {
        return bkcountremarkstate;
    }

    public void setBkcountremarkstate(int bkcountremarkstate) {
        this.bkcountremarkstate = bkcountremarkstate;
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

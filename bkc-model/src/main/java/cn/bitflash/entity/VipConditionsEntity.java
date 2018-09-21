package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("vip_conditions")
public class VipConditionsEntity implements Serializable {

    @TableId
    private String level;

    private BigDecimal power;

    private Integer bkcount;

    private String remark;

    private Integer state;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public Integer getBkcount() {
        return bkcount;
    }

    public void setBkcount(Integer bkcount) {
        this.bkcount = bkcount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

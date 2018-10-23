package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangjun
 * @date 2018年10月22日
 */
@TableName("system_vip")
public class SystemVipEntity implements Serializable {
    @TableId
    private Integer id;

    private Float vipCash;

    private String vipLevel;

    private Float hlbAmount;

    //最大算力
    private Float maxPower;
    //最小算力
    private Float minPower;

    //欢乐币赠送比例
    private BigDecimal hlbGiveRate;

    private String remark;

    private Float initPower;

    //算力步长
    private Float powerStep;

    //人数步长
    private Float peopleStep;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getVipCash() {
        return vipCash;
    }

    public void setVipCash(Float vipCash) {
        this.vipCash = vipCash;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Float getHlbAmount() {
        return hlbAmount;
    }

    public void setHlbAmount(Float hlbAmount) {
        this.hlbAmount = hlbAmount;
    }

    public Float getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Float maxPower) {
        this.maxPower = maxPower;
    }

    public Float getMinPower() {
        return minPower;
    }

    public void setMinPower(Float minPower) {
        this.minPower = minPower;
    }

    public BigDecimal getHlbGiveRate() {
        return hlbGiveRate;
    }

    public void setHlbGiveRate(BigDecimal hlbGiveRate) {
        this.hlbGiveRate = hlbGiveRate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Float getInitPower() {
        return initPower;
    }

    public void setInitPower(Float initPower) {
        this.initPower = initPower;
    }

    public Float getPowerStep() {
        return powerStep;
    }

    public void setPowerStep(Float powerStep) {
        this.powerStep = powerStep;
    }

    public Float getPeopleStep() {
        return peopleStep;
    }

    public void setPeopleStep(Float peopleStep) {
        this.peopleStep = peopleStep;
    }
}

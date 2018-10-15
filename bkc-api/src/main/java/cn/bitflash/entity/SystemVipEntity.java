package cn.bitflash.entity;

import java.math.BigDecimal;

public class SystemVipEntity {

    private Integer id;

    private Float vipCash;

    private String vipLevel;

    private Float hlbAmount;

    //最大算力
    private Integer maxPower;
    //最小算力
    private Integer minPower;

    //欢乐币赠送比例
    private BigDecimal hlbGiveRate;

    private String remark;

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

    public Integer getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Integer maxPower) {
        this.maxPower = maxPower;
    }

    public Integer getMinPower() {
        return minPower;
    }

    public void setMinPower(Integer minPower) {
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
}

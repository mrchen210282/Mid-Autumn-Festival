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

    private float vipCash;

    private String vipLevel;

    private float hlbAmount;

    //最大算力
    private BigDecimal maxPower;
    //最小算力
    private BigDecimal minPower;

    //欢乐币赠送比例
    private BigDecimal hlbGiveRate;

    private String remark;

    public float getHlbAmount() {
        return hlbAmount;
    }

    public void setHlbAmount(float hlbAmount) {
        this.hlbAmount = hlbAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getVipCash() {
        return vipCash;
    }

    public void setVipCash(float vipCash) {
        this.vipCash = vipCash;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public BigDecimal getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(BigDecimal maxPower) {
        this.maxPower = maxPower;
    }

    public BigDecimal getMinPower() {
        return minPower;
    }

    public void setMinPower(BigDecimal minPower) {
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

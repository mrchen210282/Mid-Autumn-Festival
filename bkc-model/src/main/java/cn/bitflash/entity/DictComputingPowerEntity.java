package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("dict_computing_power")
public class DictComputingPowerEntity implements Serializable {
    @TableId
    private Integer level;

    private BigDecimal rate;

    private String power;

    private BigDecimal cashBenchmark;

    private String performanceBenchmark;

    private String comment;

    private String remark;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public BigDecimal getCashBenchmark() {
        return cashBenchmark;
    }

    public void setCashBenchmark(BigDecimal cashBenchmark) {
        this.cashBenchmark = cashBenchmark;
    }

    public String getPerformanceBenchmark() {
        return performanceBenchmark;
    }

    public void setPerformanceBenchmark(String performanceBenchmark) {
        this.performanceBenchmark = performanceBenchmark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

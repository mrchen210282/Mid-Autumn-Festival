package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("price_linechart")
public class PriceLinechartEntity implements Serializable {
    @TableId
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private LocalDateTime rateTime;

    private BigDecimal price;

    private BigDecimal rate;

    private BigDecimal bkc;

    private BigDecimal cny;

    private BigDecimal us;


    public void setRateTime(LocalDateTime rateTime) {
        this.rateTime = rateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getBkc() {
        return bkc;
    }

    public void setBkc(BigDecimal bkc) {
        this.bkc = bkc;
    }

    public BigDecimal getCny() {
        return cny;
    }

    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    public BigDecimal getUs() {
        return us;
    }

    public void setUs(BigDecimal us) {
        this.us = us;
    }
}


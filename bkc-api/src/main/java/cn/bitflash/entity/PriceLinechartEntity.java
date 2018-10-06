package cn.bitflash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceLinechartEntity implements Serializable {

    private static final long serialVersionUID = 8348757757253519053L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private LocalDateTime rateTime;

    private float price;

    private float rate;

    private BigDecimal cny;
    private String cnyStr;

    private float bkc;

    private BigDecimal us;
    private String usStr;

    //转换为String类型
    private String rateStr;

    private int symbol;

    public LocalDateTime getRateTime() {
        return rateTime;
    }

    public void setRateTime(LocalDateTime rateTime) {
        this.rateTime = rateTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public BigDecimal getCny() {
        return cny;
    }

    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    public float getBkc() {
        return bkc;
    }

    public void setBkc(float bkc) {
        this.bkc = bkc;
    }

    public BigDecimal getUs() {
        return us;
    }

    public void setUs(BigDecimal us) {
        this.us = us;
    }

    public String getRateStr() {
        return rateStr;
    }

    public void setRateStr(String rateStr) {
        this.rateStr = rateStr;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public String getCnyStr() {
        return cnyStr;
    }

    public void setCnyStr(String cnyStr) {
        this.cnyStr = cnyStr;
    }

    public String getUsStr() {
        return usStr;
    }

    public void setUsStr(String usStr) {
        this.usStr = usStr;
    }
}

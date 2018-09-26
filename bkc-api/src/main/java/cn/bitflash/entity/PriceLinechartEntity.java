package cn.bitflash.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PriceLinechartEntity implements Serializable {

    private static final long serialVersionUID = 8348757757253519053L;

    private LocalDateTime rateTime;

    private float price;

    private float rate;

    private float cny;

    private float bkc;

    //转换为String类型
    private String rateStr;

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

    public float getCny() {
        return cny;
    }

    public void setCny(float cny) {
        this.cny = cny;
    }

    public float getBkc() {
        return bkc;
    }

    public void setBkc(float bkc) {
        this.bkc = bkc;
    }

    public String getRateStr() {
        return rateStr;
    }

    public void setRateStr(String rateStr) {
        this.rateStr = rateStr;
    }
}

package cn.bitflash.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PriceLinechartEntity implements Serializable {

    private static final long serialVersionUID = 8348757757253519053L;

    private Float price;

    private Float rate;

    private int cny;

    private float bkc;

    private LocalDateTime rateTime;


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public LocalDateTime getRateTime() {
        return rateTime;
    }

    public void setRateTime(LocalDateTime rateTime) {
        this.rateTime = rateTime;
    }

    public int getCny() {
        return cny;
    }

    public void setCny(int cny) {
        this.cny = cny;
    }

    public float getBkc() {
        return bkc;
    }

    public void setBkc(float bkc) {
        this.bkc = bkc;
    }
}

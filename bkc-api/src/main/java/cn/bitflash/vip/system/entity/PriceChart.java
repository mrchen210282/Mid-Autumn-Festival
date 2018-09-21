package cn.bitflash.vip.system.entity;

import java.time.LocalDateTime;

public class PriceChart {

    private Float price;

    private LocalDateTime rateTime;

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LocalDateTime getRateTime() {
        return rateTime;
    }

    public void setRateTime(LocalDateTime rateTime) {
        this.rateTime = rateTime;
    }
}

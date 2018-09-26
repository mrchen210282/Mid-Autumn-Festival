package cn.bitflash.vip.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PriceChart {

    private Float price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
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

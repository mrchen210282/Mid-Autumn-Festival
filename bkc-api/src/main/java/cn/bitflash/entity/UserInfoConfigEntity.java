package cn.bitflash.entity;

import java.io.Serializable;

public class UserInfoConfigEntity implements Serializable {

    private static final long serialVersionUID = 7629853906064099813L;
    private Integer id;

    private Integer min;

    private String showPower;

    private Double giveRate;

    private String showGiveRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getShowPower() {
        return showPower;
    }

    public void setShowPower(String showPower) {
        this.showPower = showPower;
    }

    public Double getGiveRate() {
        return giveRate;
    }

    public void setGiveRate(Double giveRate) {
        this.giveRate = giveRate;
    }

    public String getShowGiveRate() {
        return showGiveRate;
    }

    public void setShowGiveRate(String showGiveRate) {
        this.showGiveRate = showGiveRate;
    }
}

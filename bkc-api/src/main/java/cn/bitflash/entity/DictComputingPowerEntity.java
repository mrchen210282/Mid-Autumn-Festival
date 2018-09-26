package cn.bitflash.entity;

import cn.bitflash.vip.level.entity.Position;

public class DictComputingPowerEntity {

    private Integer level;

    private float rate;

    private  String power;

    private String cashBenchmark;

    private Position position;

    private String comment;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getCashBenchmark() {
        return cashBenchmark;
    }

    public void setCashBenchmark(String cashBenchmark) {
        this.cashBenchmark = cashBenchmark;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

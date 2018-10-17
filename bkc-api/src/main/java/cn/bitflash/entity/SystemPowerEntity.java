package cn.bitflash.entity;

public class SystemPowerEntity {

    private Float power;

    private Integer upgradeNum;

    private Float specialPower;

    public Float getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(Float specialPower) {
        this.specialPower = specialPower;
    }

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
    }

    public Integer getUpgradeNum() {
        return upgradeNum;
    }

    public void setUpgradeNum(Integer upgradeNum) {
        this.upgradeNum = upgradeNum;
    }
}

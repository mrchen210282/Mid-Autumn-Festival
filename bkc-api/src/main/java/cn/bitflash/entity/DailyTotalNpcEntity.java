package cn.bitflash.entity;

import java.util.Date;

public class DailyTotalNpcEntity {

    private Date createTime;

    private Float totalNpc;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Float getTotalNpc() {
        return totalNpc;
    }

    public void setTotalNpc(Float totalNpc) {
        this.totalNpc = totalNpc;
    }
}
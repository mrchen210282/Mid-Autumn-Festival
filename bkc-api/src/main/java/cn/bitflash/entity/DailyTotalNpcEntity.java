package cn.bitflash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DailyTotalNpcEntity {

    @JsonFormat(pattern = "yyyy-MM-dd")
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

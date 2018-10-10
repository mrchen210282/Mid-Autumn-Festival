package cn.bitflash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class DailyTotalNpcEntity {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime;

    private Float totalNpc;

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public Float getTotalNpc() {
        return totalNpc;
    }

    public void setTotalNpc(Float totalNpc) {
        this.totalNpc = totalNpc;
    }
}

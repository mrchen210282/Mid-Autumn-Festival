package cn.bitflash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class DailyTotalNpcEntity {

    private BigDecimal totalNpc;

    private BigDecimal remainderNpc;

    public BigDecimal getTotalNpc() {
        return totalNpc;
    }

    public void setTotalNpc(BigDecimal totalNpc) {
        this.totalNpc = totalNpc;
    }

    public BigDecimal getRemainderNpc() {
        return remainderNpc;
    }

    public void setRemainderNpc(BigDecimal remainderNpc) {
        this.remainderNpc = remainderNpc;
    }
}

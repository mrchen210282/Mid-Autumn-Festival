package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;
@TableName("daily_total_npc")
public class DailyTotalNpcEntity {

    @TableId(type = IdType.INPUT)
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

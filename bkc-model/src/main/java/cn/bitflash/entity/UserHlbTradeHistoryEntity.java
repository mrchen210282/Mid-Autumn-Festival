package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * npc兑换实体类
 */
@TableName("user_hlb_trade_history")
public class UserHlbTradeHistoryEntity {

    @TableId(type = IdType.INPUT)
    private String id;

    private String uid;

    private Float totalHlb;

    private Float totalNpc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Float getTotalHlb() {
        return totalHlb;
    }

    public void setTotalHlb(Float totalHlb) {
        this.totalHlb = totalHlb;
    }

    public Float getTotalNpc() {
        return totalNpc;
    }

    public void setTotalNpc(Float totalNpc) {
        this.totalNpc = totalNpc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

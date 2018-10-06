package cn.bitflash.entity;

import java.util.Date;

/**
 * npc兑换实体类
 */
public class UserNpcEntity {

    private String id;

    private String uid;

    private Float totalHlb;

    private Float totalNpc;

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

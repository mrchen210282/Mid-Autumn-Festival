package cn.bitflash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserGetuiEntity {

    private String uid;

    private String cid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date updateTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

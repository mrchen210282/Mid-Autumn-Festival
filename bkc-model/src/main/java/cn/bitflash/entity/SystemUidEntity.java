package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("system_uid")
public class SystemUidEntity implements Serializable {

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

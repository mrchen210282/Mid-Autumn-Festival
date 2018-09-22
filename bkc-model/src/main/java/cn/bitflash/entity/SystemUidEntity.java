package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("system_uid")
public class SystemUidEntity implements Serializable {
    @TableId
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

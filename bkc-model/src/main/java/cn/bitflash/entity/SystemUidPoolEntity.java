package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author wangjun
 * @date 2018年9月22日
 */
@TableName("system_uid_pool")
public class SystemUidPoolEntity {

    @TableId
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


}

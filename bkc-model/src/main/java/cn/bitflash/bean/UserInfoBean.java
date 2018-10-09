package cn.bitflash.bean;


import java.io.Serializable;
import java.util.Date;

public class UserInfoBean implements Serializable {

    private static final long serialVersionUID = 4282111755160371079L;

    private String mobile;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
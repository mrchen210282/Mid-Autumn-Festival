package cn.bitflash.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("user_secret")
public class UserSecretEntity implements Serializable {
    @TableId
    private String uid;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String password;

    private String salt;

    private String token;

    private String payPassword;

    private String isSetPaypwd;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getIsSetPaypwd() {
        return isSetPaypwd;
    }

    public void setIsSetPaypwd(String isSetPaypwd) {
        this.isSetPaypwd = isSetPaypwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

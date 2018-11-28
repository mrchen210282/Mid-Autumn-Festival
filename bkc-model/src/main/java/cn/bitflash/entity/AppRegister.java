package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

@TableName("app_register")
public class AppRegister {

    @TableId
    private String appKey;

    private String appSecret;

    private Date createTime;

    //1.登录权限 2.获取用户币种信息 3.获取用户的支付信息
    private Integer appPower;

    private String appDescribe;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAppPower() {
        return appPower;
    }

    public void setAppPower(Integer appPower) {
        this.appPower = appPower;
    }

    public String getAppDescribe() {
        return appDescribe;
    }

    public void setAppDescribe(String appDescribe) {
        this.appDescribe = appDescribe;
    }
}

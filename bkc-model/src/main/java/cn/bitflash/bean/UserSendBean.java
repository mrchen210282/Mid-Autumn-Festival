package cn.bitflash.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public class UserSendBean implements Serializable {
    private Integer id;

    private String sendUid;

    private float quantity;

    private String sendeeUid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date sendTime;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSendUid() {
        return sendUid;
    }

    public void setSendUid(String sendUid) {
        this.sendUid = sendUid;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getSendeeUid() {
        return sendeeUid;
    }

    public void setSendeeUid(String sendeeUid) {
        this.sendeeUid = sendeeUid;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

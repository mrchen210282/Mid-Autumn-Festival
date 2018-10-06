package cn.bitflash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author soso
 * @date 2018年5月22日 下午4:00:45
 */

public class UserComplaintEntity implements Serializable {

    private String orderId;
    private String complaintUid;
    private String contactsUid;
    private String complaintState;
    private String orderState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getComplaintUid() {
        return complaintUid;
    }

    public void setComplaintUid(String complaintUid) {
        this.complaintUid = complaintUid;
    }

    public String getContactsUid() {
        return contactsUid;
    }

    public void setContactsUid(String contactsUid) {
        this.contactsUid = contactsUid;
    }

    public String getComplaintState() {
        return complaintState;
    }

    public void setComplaintState(String complaintState) {
        this.complaintState = complaintState;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

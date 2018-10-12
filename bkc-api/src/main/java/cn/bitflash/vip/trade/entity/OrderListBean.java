package cn.bitflash.vip.trade.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class OrderListBean {
    private String realname;

    private String nickname;

    private String id;

    /**
     * 卖出价格
     */
    private BigDecimal price;

    /**
     * 卖出数量
     */
    private BigDecimal quantity;

    private String uid;

    private String purchaseUid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String state;

    private String isMySelf;

    public String getPurchaseUid() {
        return purchaseUid;
    }

    public void setPurchaseUid(String purchaseUid) {
        this.purchaseUid = purchaseUid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsMySelf() {
        return isMySelf;
    }

    public void setIsMySelf(String isMySelf) {
        this.isMySelf = isMySelf;
    }
}

package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author soso
 * @date 2018年5月22日 下午4:00:45
 */
@TableName("user_market_trade_history")
public class UserMarketTradeHistoryEntity implements Serializable {
    @TableId(type = IdType.INPUT)
    private String userTradeId;
    private String purchaseUid;
    private float price;
    private String sellUid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;
    private float purchaseQuantity;
    private float sellQuantity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date finishTime;
    private String orderState;

    public String getUserTradeId() {
        return userTradeId;
    }

    public void setUserTradeId(String userTradeId) {
        this.userTradeId = userTradeId;
    }

    public String getPurchaseUid() {
        return purchaseUid;
    }

    public void setPurchaseUid(String purchaseUid) {
        this.purchaseUid = purchaseUid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSellUid() {
        return sellUid;
    }

    public void setSellUid(String sellUid) {
        this.sellUid = sellUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public float getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(float purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public float getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(float sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}

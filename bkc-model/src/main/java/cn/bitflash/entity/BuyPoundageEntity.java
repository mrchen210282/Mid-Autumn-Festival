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
@TableName("buy_poundage")
public class BuyPoundageEntity implements Serializable {
    @TableId(type = IdType.INPUT)
    private String userBuyId;
    private String sellUid;
    private float poundage;
    private float quantity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;

    public String getUserBuyId() {
        return userBuyId;
    }

    public void setUserBuyId(String userBuyId) {
        this.userBuyId = userBuyId;
    }

    public String getSellUid() {
        return sellUid;
    }

    public void setSellUid(String sellUid) {
        this.sellUid = sellUid;
    }

    public float getPoundage() {
        return poundage;
    }

    public void setPoundage(float poundage) {
        this.poundage = poundage;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

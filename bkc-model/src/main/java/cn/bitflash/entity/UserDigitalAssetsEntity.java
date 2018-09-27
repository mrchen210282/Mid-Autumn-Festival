package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("user_digital_assets")
public class UserDigitalAssetsEntity implements Serializable {
    @TableId
    private String uid;

    private BigDecimal purchase;

    private BigDecimal frozen;

    private BigDecimal totelRelease;

    private BigDecimal available;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getPurchase() {
        return purchase;
    }

    public void setPurchase(BigDecimal purchase) {
        this.purchase = purchase;
    }

    public BigDecimal getFrozen() {
        return frozen;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }

    public BigDecimal getTotelRelease() {
        return totelRelease;
    }

    public void setTotelRelease(BigDecimal totelRelease) {
        this.totelRelease = totelRelease;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }
}

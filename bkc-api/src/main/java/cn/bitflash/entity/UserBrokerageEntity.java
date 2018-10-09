

package cn.bitflash.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangjun
 * @date 2018.10.8
 */

public class UserBrokerageEntity implements Serializable {

    private Integer id;

    private BigDecimal purchaseBrokerage;

    private BigDecimal sellBrokerage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPurchaseBrokerage() {
        return purchaseBrokerage;
    }

    public void setPurchaseBrokerage(BigDecimal purchaseBrokerage) {
        this.purchaseBrokerage = purchaseBrokerage;
    }

    public BigDecimal getSellBrokerage() {
        return sellBrokerage;
    }

    public void setSellBrokerage(BigDecimal sellBrokerage) {
        this.sellBrokerage = sellBrokerage;
    }
}
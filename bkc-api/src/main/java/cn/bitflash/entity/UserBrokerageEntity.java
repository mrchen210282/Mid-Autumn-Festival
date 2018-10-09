

package cn.bitflash.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangjun
 * @date 2018.10.8
 */

public class UserBrokerageEntity implements Serializable {

    private Integer id;

    private Float purchaseBrokerage;

    private Float sellBrokerage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPurchaseBrokerage() {
        return purchaseBrokerage;
    }

    public void setPurchaseBrokerage(Float purchaseBrokerage) {
        this.purchaseBrokerage = purchaseBrokerage;
    }

    public Float getSellBrokerage() {
        return sellBrokerage;
    }

    public void setSellBrokerage(Float sellBrokerage) {
        this.sellBrokerage = sellBrokerage;
    }
}
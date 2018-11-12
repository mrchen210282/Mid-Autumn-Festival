package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @author wangjun
 * @date 2018年10月06日 下午4:00:45
 */
@TableName("user_brokerage")
public class UserBrokerageEntity {

    @TableId(type = IdType.INPUT)
    private String id;

    private Float purchaseBrokerage;

    private Float sellBrokerage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

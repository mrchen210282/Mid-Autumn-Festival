package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("dict_computing_power")
public class DictComputingPowerEntity implements Serializable {
    @TableId
    private Integer id;

    private String key;

    private BigDecimal power;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }
}

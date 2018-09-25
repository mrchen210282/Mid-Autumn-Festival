package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("user_shared_income_pool")
public class UserSharedIncomePoolEntity implements Serializable {
    @TableId
    private BigInteger id;

    private BigInteger pool;

    private BigInteger totalNpc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getPool() {
        return pool;
    }

    public void setPool(BigInteger pool) {
        this.pool = pool;
    }

    public BigInteger getTotalNpc() {
        return totalNpc;
    }

    public void setTotalNpc(BigInteger totalNpc) {
        this.totalNpc = totalNpc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

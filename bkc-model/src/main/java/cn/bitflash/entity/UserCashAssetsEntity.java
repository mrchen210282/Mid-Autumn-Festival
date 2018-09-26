package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("user_cash_assets")
public class UserCashAssetsEntity implements Serializable {
    private static final long serialVersionUID = -7641609719739406440L;

    @TableId(type = IdType.INPUT)
    private String uid;

    private BigDecimal purchase;

    private BigDecimal totleIncome;

    private BigDecimal differenceIncome;

    private BigDecimal sharedIncome;

    private BigDecimal lotto;

    private Integer powerLevel;

    //可提现
    private BigDecimal withdrawCash;

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

    public BigDecimal getTotleIncome() {
        return totleIncome;
    }

    public void setTotleIncome(BigDecimal totleIncome) {
        this.totleIncome = totleIncome;
    }

    public BigDecimal getDifferenceIncome() {
        return differenceIncome;
    }

    public void setDifferenceIncome(BigDecimal differenceIncome) {
        this.differenceIncome = differenceIncome;
    }

    public BigDecimal getSharedIncome() {
        return sharedIncome;
    }

    public void setSharedIncome(BigDecimal sharedIncome) {
        this.sharedIncome = sharedIncome;
    }

    public BigDecimal getLotto() {
        return lotto;
    }

    public void setLotto(BigDecimal lotto) {
        this.lotto = lotto;
    }

    public Integer getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(Integer powerLevel) {
        this.powerLevel = powerLevel;
    }

    public BigDecimal getWithdrawCash() {
        return withdrawCash;
    }

    public void setWithdrawCash(BigDecimal withdrawCash) {
        this.withdrawCash = withdrawCash;
    }
}

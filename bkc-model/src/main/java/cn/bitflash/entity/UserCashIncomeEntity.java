package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("user_cash_income")
public class UserCashIncomeEntity implements Serializable {
    @TableId
    private String uid;

    private BigDecimal totelIncome;

    private BigDecimal dailyIncome;

    private String stageHead;

    private String shareBenefit;

    private BigDecimal power;

    private BigDecimal totalIncome;

    private String lineF;

    private String lineS;

    private String lineT;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getTotelIncome() {
        return totelIncome;
    }

    public void setTotelIncome(BigDecimal totelIncome) {
        this.totelIncome = totelIncome;
    }

    public BigDecimal getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(BigDecimal dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public String getStageHead() {
        return stageHead;
    }

    public void setStageHead(String stageHead) {
        this.stageHead = stageHead;
    }

    public String getShareBenefit() {
        return shareBenefit;
    }

    public void setShareBenefit(String shareBenefit) {
        this.shareBenefit = shareBenefit;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getLineF() {
        return lineF;
    }

    public void setLineF(String lineF) {
        this.lineF = lineF;
    }

    public String getLineS() {
        return lineS;
    }

    public void setLineS(String lineS) {
        this.lineS = lineS;
    }

    public String getLineT() {
        return lineT;
    }

    public void setLineT(String lineT) {
        this.lineT = lineT;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

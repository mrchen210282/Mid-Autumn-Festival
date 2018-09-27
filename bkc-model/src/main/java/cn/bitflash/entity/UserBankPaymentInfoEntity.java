package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("user_bank_payment_info")
public class UserBankPaymentInfoEntity implements Serializable {
    @TableId(type = IdType.INPUT)
    private String uid;

    private String bank;

    private String cardNo;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}

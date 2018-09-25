package cn.bitflash.vip.user.entity;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class CashForm {

    //提现金额
    @NotBlank(message = "提现金额不能为空")
    private BigDecimal cashMoney;

    //login passwd
    @NotBlank(message = "登录密码不能为空")
    private String passwd;

    //提现类别
    @NotBlank(message = "提现方式为空")
    private String cashType;

    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCashType() {
        return cashType;
    }

    public void setCashType(String cashType) {
        this.cashType = cashType;
    }
}

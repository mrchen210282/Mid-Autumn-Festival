package cn.bitflash.vip.user.entity;

import java.math.BigDecimal;

public class NpcForm {

    private BigDecimal npc;

    private BigDecimal hlb;

    private BigDecimal expense;

    private String password;

    public BigDecimal getNpc() {
        return npc;
    }

    public void setNpc(BigDecimal npc) {
        this.npc = npc;
    }

    public BigDecimal getHlb() {
        return hlb;
    }

    public void setHlb(BigDecimal hlb) {
        this.hlb = hlb;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }
}

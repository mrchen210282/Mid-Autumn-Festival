package cn.bitflash.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public class UserPerformanceEntity implements Serializable {

    private String uid;

    private BigDecimal line1;

    private BigDecimal line2;

    private BigDecimal line3;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getLine1() {
        return line1;
    }

    public void setLine1(BigDecimal line1) {
        this.line1 = line1;
    }

    public BigDecimal getLine2() {
        return line2;
    }

    public void setLine2(BigDecimal line2) {
        this.line2 = line2;
    }

    public BigDecimal getLine3() {
        return line3;
    }

    public void setLine3(BigDecimal line3) {
        this.line3 = line3;
    }
}

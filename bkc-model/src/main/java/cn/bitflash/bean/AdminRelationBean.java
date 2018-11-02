package cn.bitflash.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class AdminRelationBean {

    /**
     * 用户id
     */
    private String uid;

    /**
     * 邀请码
     */
    private String invitation_code;

    /**
     * 左侧
     */
    private int lft;

    /**
     * 右侧
     */
    private int rgt;

    /**
     * 层级
     */
    private int layer;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 左节点
     *
     * @return
     */
    private AdminRelationBean lftChild;

    /**
     * 右节点
     *
     * @return
     */
    private AdminRelationBean rgtChild;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public AdminRelationBean getLftChild() {
        return lftChild;
    }

    public void setLftChild(AdminRelationBean lftChild) {
        this.lftChild = lftChild;
    }

    public AdminRelationBean getRgtChild() {
        return rgtChild;
    }

    public void setRgtChild(AdminRelationBean rgtChild) {
        this.rgtChild = rgtChild;
    }

    public String getUid() {
        return uid;

    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getInvitation_code() {
        return invitation_code;
    }

    public void setInvitation_code(String invitation_code) {
        this.invitation_code = invitation_code;
    }

    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    @Override
    public String toString() {
        return "SysUserRelationEntity{" +
                "uid='" + uid + '\'' +
                ", invitation_code='" + invitation_code + '\'' +
                ", lft=" + lft +
                ", rgt=" + rgt +
                ", layer=" + layer +
                ", realname='" + realname + '\'' +
                ", lftChild=" + lftChild +
                ", rgtChild=" + rgtChild +
                '}';
    }
}
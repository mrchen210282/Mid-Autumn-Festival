package cn.bitflash.entity;

import java.io.Serializable;

/**
 * 用户体系
 *
 * @author soso
 * @date 2018年5月26日 上午10:36:57
 */
public class UserRelationEntity implements Serializable {

    /**
     * 用户id
     */
    private String uid;

    /**
     * 直推邀请码
     */
    private String fatherCode;

    /**
     * 左边界值
     */
    private Integer lft;

    /**
     * 右边界值
     */
    private Integer rgt;

    /**
     * 层
     */
    private Integer layer;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFatherCode() {
        return fatherCode;
    }

    public void setFatherCode(String fatherCode) {
        this.fatherCode = fatherCode;
    }

    public Integer getLft() {
        return lft;
    }

    public void setLft(Integer lft) {
        this.lft = lft;
    }

    public Integer getRgt() {
        return rgt;
    }

    public void setRgt(Integer rgt) {
        this.rgt = rgt;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

}

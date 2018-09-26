package cn.bitflash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public class SystemAppBannerEntity {

    private Integer id;

    private String bannerAddress;

    private String opendAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannerAddress() {
        return bannerAddress;
    }

    public void setBannerAddress(String bannerAddress) {
        this.bannerAddress = bannerAddress;
    }

    public String getOpendAddress() {
        return opendAddress;
    }

    public void setOpendAddress(String opendAddress) {
        this.opendAddress = opendAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

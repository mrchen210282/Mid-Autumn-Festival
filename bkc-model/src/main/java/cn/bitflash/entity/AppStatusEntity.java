package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("app_status")
public class AppStatusEntity implements Serializable {
    @TableId(type = IdType.INPUT)
    private String appid;

    private String version;

    private String url;

    private String note;

    private String title;

    public String getAppId() {
        return appid;
    }

    public void setAppId(String appId) {
        this.appid = appId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

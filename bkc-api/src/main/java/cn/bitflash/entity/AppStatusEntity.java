package cn.bitflash.entity;

import java.io.Serializable;

/**
 * @author soso
 * @date 2018年5月22日 下午4:00:45
 */

public class AppStatusEntity implements Serializable {


    private String appid;

    private String version;

    private String url;

    private String note;

    private String title;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
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

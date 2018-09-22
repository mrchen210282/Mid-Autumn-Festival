package cn.bitflash.entity;


import java.io.Serializable;
import java.util.Date;

public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID = 4282111755160371079L;

    private String uid;

    private String realname;

    private String idNumber;

    private String invitationCode;

    private String isInvitated;

    private String avatar;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getIsInvitated() {
        return isInvitated;
    }

    public void setIsInvitated(String isInvitated) {
        this.isInvitated = isInvitated;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
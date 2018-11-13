package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("user_info")
public class UserInfoEntity implements Serializable {
    @TableId(type = IdType.INPUT)
    private String uid;

    private String realname;

    private String mobile;

    private String nickname;

    private String idNumber;

    private String isInvited;

    private String invitationCode;

    private String isAuth;

    //头像
    private String avatar;

    private Integer upgradeNum;

    private Integer vipLevel;

    private Float currentPower;

    private String area;

    private String nicklock;

    //npc释放比率
    private Integer npcRelease;

    //hlb释放比率
    private Integer hlbRelease;

    private String isAvailable;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(String isInvited) {
        this.isInvited = isInvited;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUpgradeNum() {
        return upgradeNum;
    }

    public void setUpgradeNum(Integer upgradeNum) {
        this.upgradeNum = upgradeNum;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Float getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(Float currentPower) {
        this.currentPower = currentPower;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNicklock() {
        return nicklock;
    }

    public void setNicklock(String nicklock) {
        this.nicklock = nicklock;
    }

    public Integer getNpcRelease() {
        return npcRelease;
    }

    public void setNpcRelease(Integer npcRelease) {
        this.npcRelease = npcRelease;
    }

    public Integer getHlbRelease() {
        return hlbRelease;
    }

    public void setHlbRelease(Integer hlbRelease) {
        this.hlbRelease = hlbRelease;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }
}

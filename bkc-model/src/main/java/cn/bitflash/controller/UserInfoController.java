package cn.bitflash.controller;


import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserInfoService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYUGUO
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userInfo/selectById")
    public UserInfoEntity selectById(@RequestParam("id") String id) {
        UserInfoEntity entity = userInfoService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userInfo/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUid(json.getString("uid"));
        entity.setRealname(json.getString("realname"));
        entity.setNickname(json.getString("nickname"));
        entity.setNicklock(json.getString("nicklock"));
        entity.setIdNumber(json.getString("idNumber"));
        entity.setMobile(json.getString("mobile"));
        entity.setIsVip(json.getString("isVip"));
        entity.setInvitationCode(json.getString("invitationCode"));
        entity.setIsInvitation(json.getBoolean("isInvitation"));
        entity.setIsAuthentication(json.getString("isAuthentication"));
        entity.setVipCreateTime(json.getDate("vipCreateTime"));
        entity.setAuthenticationTime(json.getDate("authenticationTime"));
        userInfoService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userInfo/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUid(json.getString("uid"));
        entity.setRealname(json.getString("realname"));
        entity.setNickname(json.getString("nickname"));
        entity.setNicklock(json.getString("nicklock"));
        entity.setIdNumber(json.getString("idNumber"));
        entity.setMobile(json.getString("mobile"));
        entity.setVipCreateTime(json.getDate("vipCreateTime"));
        entity.setAuthenticationTime(json.getDate("authenticationTime"));
        entity.setIsVip(json.getString("isVip"));
        entity.setIsInvitation(json.getBoolean("isInvitation"));
        entity.setIsAuthentication(json.getString("isAuthentication"));
        entity.setInvitationCode(json.getString("invitationCode"));
        userInfoService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userInfo/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        userInfoService.deleteById(id);
    }

    /**
     * insertInfoIndex
     */
    @PostMapping("/inner/userInfouser/insertInfoIndex")
    Boolean insertInfoIndex(@RequestParam("uid")String uid,@RequestParam("mobile")String mobile,@RequestParam("isInvitation")boolean flag,@RequestParam("name")String name){
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUid(uid);
        entity.setMobile(mobile);
        entity.setIsInvitation(flag);
        entity.setNickname(name);
        entity.setRealname(name);
        return userInfoService.insert(entity);
    }

    /**
     * insertInfoCode
     */
    @PostMapping("/inner/userInfouser/insertInfoCode")
    Boolean insertInfoCode(@RequestParam("uid")String uid,@RequestParam("mobile")String mobile,
                           @RequestParam("isInvitation")boolean flag,@RequestParam("name")String name
            ,@RequestParam("code")String code){
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUid(uid);
        entity.setMobile(mobile);
        entity.setIsInvitation(flag);
        entity.setNickname(name);
        entity.setRealname(name);
        entity.setInvitationCode(code);
        return userInfoService.insert(entity);
    }


}

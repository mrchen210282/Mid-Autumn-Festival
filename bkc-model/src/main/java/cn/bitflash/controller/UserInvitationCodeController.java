package cn.bitflash.controller;


import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserInvitationCodeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
public class UserInvitationCodeController {

    @Autowired
    private UserInvitationCodeService userInvitationCodeService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userInvitationCode/selectById")
    public UserInvitationCodeEntity selectById(@RequestParam("id") String id) {
        UserInvitationCodeEntity entity = userInvitationCodeService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userInvitationCode/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserInvitationCodeEntity entity = new UserInvitationCodeEntity();
        entity.setUid(json.getString("uid"));
        entity.setLftCode(json.getString("lftCode"));
        entity.setRgtCode(json.getString("rgtCode"));
        userInvitationCodeService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userInvitationCode/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserInvitationCodeEntity entity = new UserInvitationCodeEntity();
        entity.setUid(json.getString("uid"));
        entity.setLftCode(json.getString("lftCode"));
        entity.setRgtCode(json.getString("rgtCode"));
        userInvitationCodeService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userInvitationCode/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        userInvitationCodeService.deleteById(id);
    }

    /**
     * selectCodeByCode
     */
    @PostMapping("/inner/userInvitationCode/selectCodeByCode")
    UserInvitationCodeEntity selectCodeByCode(@RequestParam("code")String code){
        UserInvitationCodeEntity entity = userInvitationCodeService.selectOne(new EntityWrapper<UserInvitationCodeEntity>().eq("lft_code",code));
        if(entity == null){
            entity = userInvitationCodeService.selectOne(new EntityWrapper<UserInvitationCodeEntity>().eq("rgt_code",code));
        }
        return entity;
    }

}

package cn.bitflash.controller;


import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.service.UserInvitationCodeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
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
    public void updateById(@RequestBody JSONObject json) throws Exception {
        UserInvitationCodeEntity entity = (UserInvitationCodeEntity) JSONObject.parseObject(json.toString(), UserInvitationCodeEntity.class);
        userInvitationCodeService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userInvitationCode/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        UserInvitationCodeEntity entity = (UserInvitationCodeEntity) JSONObject.parseObject(json.toString(), UserInvitationCodeEntity.class);
        userInvitationCodeService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userInvitationCode/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        userInvitationCodeService.deleteById(id);
    }

}

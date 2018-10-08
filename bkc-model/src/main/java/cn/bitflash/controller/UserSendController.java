package cn.bitflash.controller;

import cn.bitflash.entity.UserSendEntity;
import cn.bitflash.service.UserSendService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserSendController {

    @Autowired
    private UserSendService userSendService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userSend/selectById")
    public UserSendEntity selectById(@RequestParam("id") String id) {
        UserSendEntity entity = userSendService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userSend/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserSendEntity entity = (UserSendEntity) JSONObject.parseObject(json.toString(), UserSendEntity.class);
        return userSendService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userSend/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserSendEntity entity = (UserSendEntity) JSONObject.parseObject(json.toString(), UserSendEntity.class);
        return userSendService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userSend/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userSendService.deleteById(id);
    }


    /**
     *selectAccount
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userSend/selectAccount")
    public List<UserSendEntity> selectAccount(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages){
        List<UserSendEntity> userSendEntities = userSendService.selectAccount(uid,pages);
        return userSendEntities;
    }

    /**
     *selectaccept
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userSend/selectAccept")
    public List<UserSendEntity> selectAccept(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages){
        List<UserSendEntity> userSendEntities = userSendService.selectAccept(uid,pages);
        return userSendEntities;
    }

    /**
     *selectaccountcount
     */
    @PostMapping("/inner/userSend/selectAccountCount")
    public Integer selectAccountCount(@RequestParam("uid")String uid){
        return userSendService.selectAccountCount(uid);
    }

    /**
     * selectacceptcount
     */
    @PostMapping("/inner/userSend/selectAcceptCount")
    public Integer selectAcceptCount(@RequestParam("uid")String send_uid){
        return userSendService.selectAcceptCount(send_uid);
    }

}

package cn.bitflash.controller;


import cn.bitflash.entity.UserMobilePaymentInfoEntity;
import cn.bitflash.service.UserMobilePaymentInfoService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
public class UserMobilePaymentInfoController {

    @Autowired
    private UserMobilePaymentInfoService userMobilePaymentInfoService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userMobilePaymentInfo/selectById")
    public UserMobilePaymentInfoEntity selectById(@RequestParam("id") String id) {
        UserMobilePaymentInfoEntity entity = userMobilePaymentInfoService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userMobilePaymentInfo/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserMobilePaymentInfoEntity entity = (UserMobilePaymentInfoEntity) JSONObject.parseObject(json.toString(), UserMobilePaymentInfoEntity.class);
        return userMobilePaymentInfoService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userMobilePaymentInfo/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserMobilePaymentInfoEntity entity = (UserMobilePaymentInfoEntity) JSONObject.parseObject(json.toString(), UserMobilePaymentInfoEntity.class);
        return userMobilePaymentInfoService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userMobilePaymentInfo/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userMobilePaymentInfoService.deleteById(id);
    }

    /**
     *selectPaymentByUidAndType
     * @return
     */
    @PostMapping("/inner/userMobilePaymentInfo/selectPaymentByUidAndType")
    public UserMobilePaymentInfoEntity selectPaymentByUidAndType(@RequestParam("uid")String uid, @RequestParam("type") String type){
        UserMobilePaymentInfoEntity userMobilePaymentInfoEntity = userMobilePaymentInfoService.selectOne(new EntityWrapper<UserMobilePaymentInfoEntity>().eq("uid",uid).eq("type",type));
        return userMobilePaymentInfoEntity;
    }

    /**
     * selectPaymentsByUid
     * @param id
     * @return
     */
    @PostMapping("/inner/userMobilePaymentInfo/selectPaymentsByUid")
    List<UserMobilePaymentInfoEntity> selectPaymentsByUid(@RequestParam("id")String id){
        List<UserMobilePaymentInfoEntity> userMobilePaymentInfoEntities = userMobilePaymentInfoService.selectList(new EntityWrapper<UserMobilePaymentInfoEntity>().eq("uid",id));
        return userMobilePaymentInfoEntities;
    }

}

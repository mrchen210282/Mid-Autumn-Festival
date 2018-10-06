package cn.bitflash.controller;

import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.bean.UserComplaintBean;
import cn.bitflash.entity.BuyPoundageEntity;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.service.BuyPoundageService;
import cn.bitflash.service.UserComplaintService;
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
public class UserComplaintController {

    @Autowired
    private UserComplaintService userComplaintService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userComplaint/selectById")
    public UserComplaintEntity selectById(@RequestParam("id") String id) {
        UserComplaintEntity entity = userComplaintService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userComplaint/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserComplaintEntity entity = (UserComplaintEntity) JSONObject.parseObject(json.toString(), UserComplaintEntity.class);
        return userComplaintService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userComplaint/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserComplaintEntity entity = (UserComplaintEntity) JSONObject.parseObject(json.toString(), UserComplaintEntity.class);
        return userComplaintService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userComplaint/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userComplaintService.deleteById(id);
    }

    /**
     * selectAppealList
     */
    @PostMapping("/inner/userComplaint/selectAppealList")
    public List<UserBuyBean> selectAppealList(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages){
        return userComplaintService.selectAppealList(uid,pages);
    }

    /**
     * selectAppealCount
     */
    @PostMapping("/inner/userComplaint/selectAppealCount")
    public Integer selectAppealCount(@RequestParam("uid") String uid){
        return userComplaintService.selectAppealCount(uid);
    }

    /**
     * getComplaintMessage
     */
    @PostMapping("/inner/userComplaint/getComplaintMessage")
    public UserComplaintBean getComplaintMessage(@RequestParam("id") String id){
        return userComplaintService.getComplaintMessage(id);
    }

}

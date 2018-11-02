package cn.bitflash.controller;

import cn.bitflash.bean.AdminOrderBean;
import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.bean.UserComplaintBean;
import cn.bitflash.bean.UserSuccessBean;
import cn.bitflash.entity.BuyPoundageEntity;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.service.BuyPoundageService;
import cn.bitflash.service.UserComplaintService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/inner/userMarketBuyHistory/selectAppealPrompt")
    int selectAppealPrompt(@RequestParam("uid") String uid){
        return userComplaintService.selectAppealPrompt(uid);
    }


    /**
     * admin
     * apiComplaintList
     */
    @GetMapping("/inner/userComplaint/apiComplaintList/{page}")
    public List<AdminOrderBean> apiComplaintList(@PathVariable String page) {
        List<AdminOrderBean> list = userComplaintService.apiComplaintList(Integer.parseInt(page));
        return list;
    }

    /**
     * admin
     * apiComplaintCount
     */
    @GetMapping("/inner/userComplaint/apiComplaintCount")
    public Integer apiComplaintCount() {
        Integer count = userComplaintService.apiComplaintCount();
        return count;
    }

    /**
     * admin
     * apiComplaintSearch
     */
    @GetMapping("/inner/userComplaint/apiComplaintSearch/{page}/{id}")
    public List<AdminOrderBean> apiComplaintSearch(@PathVariable String page,@PathVariable String id) {
        List<AdminOrderBean> list = userComplaintService.apiComplaintSearch(Integer.parseInt(page),id);
        return list;
    }

    /**
     * selectSearchCount
     */
    @GetMapping("/inner/userComplaint/selectSearchCount/{id}")
    public Integer selectSearchCount(@PathVariable String id){
        return userComplaintService.selectAppealCount(id);
    }

    /**
     * deleteComplaint
     */
    @GetMapping("/inner/userComplaint/deleteComplaint/{id}")
    public Boolean deleteComplaint(@PathVariable String id){
        return userComplaintService.deleteById(id);
    }

}

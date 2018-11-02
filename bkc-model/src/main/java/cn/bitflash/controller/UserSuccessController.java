package cn.bitflash.controller;

import cn.bitflash.bean.AdminOrderBean;
import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.bean.UserSuccessBean;
import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.service.UserAssetsNpcService;
import cn.bitflash.service.UserSuccessService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserSuccessController {

    @Autowired
    private UserSuccessService userSuccessService;

    @PostMapping("/inner/userSuccess/selectSuccessList")
    public List<UserSuccessBean> selectSuccessList(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages){
        return userSuccessService.selectSuccessList(uid,pages);
    }
    @PostMapping("/inner/userSuccess/selectSuccessCount")
    public Integer selectSuccessCount(@RequestParam("uid") String uid){
        return userSuccessService.selectSuccessCount(uid);
    }
    @PostMapping("/inner/userSuccess/getSuccessMessage")
    UserSuccessBean getSuccessMessage(@RequestParam("id") String uid,@RequestParam("state") String state){
        return userSuccessService.getSuccessMessage( uid,state);
    }

    /**
     * admin
     * apiSuccessList
     */
    @GetMapping("/inner/userSuccess/apiSuccessList/{page}")
    public List<UserSuccessBean> apiSuccessList(@PathVariable String page) {
        List<UserSuccessBean> list = userSuccessService.apiSuccessList(Integer.parseInt(page));
        return list;
    }

    /**
     * admin
     * apiSuccessListCount
     */
    @GetMapping("/inner/userSuccess/apiSuccessListCount")
    public Integer apiSuccessListCount() {
        Integer count = userSuccessService.apiSuccessListCount();
        return count;
    }

    /**
     * admin
     * apiSuccessSearch
     */
    @GetMapping("/inner/userSuccess/apiSuccessSearch/{page}/{id}")
    public List<UserSuccessBean> apiSuccessSearch(@PathVariable String page,@PathVariable String id) {
        List<UserSuccessBean> list = userSuccessService.apiSuccessSearch(Integer.parseInt(page),id);
        return list;
    }

    /**
     * apiSearchCount
     *
     */
    @GetMapping("/inner/userSuccess/apiSearchCount/{id}")
    public Integer apiSearchCount(@PathVariable String id){
        return userSuccessService.selectSuccessCount(id);
    }
}

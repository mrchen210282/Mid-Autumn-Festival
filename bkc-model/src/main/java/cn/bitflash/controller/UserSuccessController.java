package cn.bitflash.controller;

import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.bean.UserSuccessBean;
import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.service.UserAssetsNpcService;
import cn.bitflash.service.UserSuccessService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

package cn.bitflash.controller;

import cn.bitflash.entity.UserBrokerageEntity;
import cn.bitflash.service.UserBrokerageService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjun
 */
@RestController
public class UserBrokerageController {

    @Autowired
    private UserBrokerageService userBrokerageService;


    /**
     * selectUserBrokerageById
     *
     * @return
     */
    @PostMapping("/inner/userBroker/selectById")
    public UserBrokerageEntity selectUserBrokerageById(@RequestParam("id") Integer id) {
        UserBrokerageEntity entity = userBrokerageService.selectById(id);
        return entity;
    }
    /**
     * updateUserBrokerage
     */
    @PostMapping("/inner/userBroker/updateUserBrokerage")
    public Boolean updateUserBrokerage(@RequestBody UserBrokerageEntity userBrokerageEntity){
        return userBrokerageService.updateById(userBrokerageEntity);
    }

    /**
     * totalBrokerage
     */
    @GetMapping("/inner/userBroker/totalBrokerage")
    public UserBrokerageEntity totalBrokerage(){
        return userBrokerageService.selectOne(new EntityWrapper<UserBrokerageEntity>().eq("id",1));
    }
}

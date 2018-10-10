package cn.bitflash.controller;

import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserBrokerageEntity;
import cn.bitflash.service.UserAssetsNpcService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAssetsNpcController {

    @Autowired
    private UserAssetsNpcService userAssetsNpcService;

    @PostMapping("/inner/userAssetsNpc/selectById")
    public UserAssetsNpcEntity selectUserAssetsNpcById(@RequestParam("id")String uid){
        return userAssetsNpcService.selectById(uid);
    }

    @PostMapping("/inner/userAssetsNpc/updateUserAssetsNpc")
    public Boolean updateUserAssetsNpc(@RequestBody JSONObject json){
        UserAssetsNpcEntity entity = (UserAssetsNpcEntity) JSONObject.parseObject(json.toString(), UserAssetsNpcEntity.class);
        return userAssetsNpcService.updateById(entity);
    }

}

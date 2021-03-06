package cn.bitflash.controller;

import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserBrokerageEntity;
import cn.bitflash.service.UserAssetsNpcService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAssetsNpcController {

    @Autowired
    private UserAssetsNpcService userAssetsNpcService;

    @PostMapping("/inner/userAssetsNpc/selectById")
    public UserAssetsNpcEntity selectUserAssetsNpcById(@RequestParam("id")String uid){
        return userAssetsNpcService.selectById(uid);
    }

    @PostMapping("/inner/userAssetsNpc/update")
    public Boolean updateUserAssetsNpc(@RequestBody JSONObject json){
        UserAssetsNpcEntity entity = (UserAssetsNpcEntity) JSONObject.parseObject(json.toString(), UserAssetsNpcEntity.class);
        return userAssetsNpcService.updateById(entity);
    }

    @PostMapping("/inner/userAssetsNpc/updateList")
    public boolean updateUserAssetsNpcList(@RequestBody List<UserAssetsNpcEntity> assetsNpcEntities){
        return userAssetsNpcService.updateBatchById(assetsNpcEntities);
    }

    /**
     * admin
     * getNPC
     */
    @GetMapping("/inner/userAssetsNpc/getNPC/{uid}")
    public UserAssetsNpcEntity getNPC(@PathVariable String uid){
        return userAssetsNpcService.selectById(uid);
    }

    /**
     * admin
     * getNPC
     */
    @PostMapping(value = "/inner/userAssetsNpc/updateNPC",consumes = "application/json")
    public Boolean getNPC(@RequestBody UserAssetsNpcEntity npc){
        return userAssetsNpcService.updateById(npc);
    }
}

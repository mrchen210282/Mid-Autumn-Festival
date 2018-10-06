package cn.bitflash.controller;

import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.service.UserAssetsNpcService;
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
    public UserAssetsNpcEntity selectbyid(@RequestParam("id")String id){
        return userAssetsNpcService.selectById(id);
    }

    @PostMapping("/inner/userAssetsNpc/update")
    public Boolean update(@RequestBody UserAssetsNpcEntity npc){
        return userAssetsNpcService.updateById(npc);
    }

}

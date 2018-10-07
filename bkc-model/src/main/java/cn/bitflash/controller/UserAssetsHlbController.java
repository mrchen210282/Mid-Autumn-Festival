package cn.bitflash.controller;

import cn.bitflash.entity.UserAssetsHlbEntity;
import cn.bitflash.service.UserAssetsHlbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAssetsHlbController {

    @Autowired
    private UserAssetsHlbService userAssetsHlbService;

    @PostMapping("/inner/userAssetsHlb/selectById")
    public UserAssetsHlbEntity selectbyid(@RequestParam("id") String id) {
        return userAssetsHlbService.selectById(id);
    }

    @PostMapping("/inner/userAssetsHlb/update")
    public Boolean update(@RequestBody UserAssetsHlbEntity npc) {
        return userAssetsHlbService.updateById(npc);
    }
}

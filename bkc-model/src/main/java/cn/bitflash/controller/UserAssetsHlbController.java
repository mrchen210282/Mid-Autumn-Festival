package cn.bitflash.controller;

import cn.bitflash.entity.UserAssetsHlbBean;
import cn.bitflash.entity.UserAssetsHlbEntity;
import cn.bitflash.service.UserAssetsHlbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/inner/userAssetsHlb/selectHlbCommunity")
    public UserAssetsHlbBean selectHlbCommunity(String id) {
        return userAssetsHlbService.selectHlbCommunity(id);
    }

    @PostMapping("/inner/userAssetsHlb/updateList")
    public boolean updateuserAssetsHlbList(@RequestBody List<UserAssetsHlbEntity> assetsHlbEntities) {
        return userAssetsHlbService.updateBatchById(assetsHlbEntities);
    }

}

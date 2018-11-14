package cn.bitflash.controller;

import cn.bitflash.entity.UserAdviseEntity;
import cn.bitflash.service.UserAdviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAdviseController {

    @Autowired
    private UserAdviseService userAdviseService;

    @PostMapping("/inner/userAdvise/insert")
    public void insert(@RequestBody UserAdviseEntity userAdviseEntity) {
        userAdviseService.insert(userAdviseEntity);
    }
}

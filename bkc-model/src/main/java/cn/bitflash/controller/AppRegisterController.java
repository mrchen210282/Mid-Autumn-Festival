package cn.bitflash.controller;

import cn.bitflash.entity.AppRegister;
import cn.bitflash.service.AppRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRegisterController {

    @Autowired
    private AppRegisterService appRegisterService;


    @PostMapping("inner/appRegister/selectById")
    public AppRegister selectAppRegister(@RequestParam("id") String id) {
        return appRegisterService.selectById(id);
    }
}

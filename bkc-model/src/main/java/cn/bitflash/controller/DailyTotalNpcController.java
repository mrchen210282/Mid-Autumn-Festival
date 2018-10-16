package cn.bitflash.controller;

import cn.bitflash.entity.DailyTotalNpcEntity;
import cn.bitflash.service.DailyTotalNpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
public class DailyTotalNpcController {

    @Autowired
    private DailyTotalNpcService dailyTotalNpcService;

    @PostMapping("/inner/dailytotalnpc/selectById")
    public DailyTotalNpcEntity selectById(@RequestParam("id")LocalDate id){
        System.out.println("------------------------------------------------------");
        System.out.println("当前系统时间为-----------"+id+"-----------------");
        System.out.println("------------------------------------------------------");
        return dailyTotalNpcService.selectById(id);
    }

    @PostMapping("/inner/dailytotalnpc/update")
    public Boolean update(@RequestBody DailyTotalNpcEntity daily){
        return dailyTotalNpcService.updateById(daily);
    }
}

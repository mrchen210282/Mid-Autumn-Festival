package cn.bitflash.controller;

import cn.bitflash.entity.DailyTotalNpcEntity;
import cn.bitflash.service.DailyTotalNpcService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class DailyTotalNpcController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DailyTotalNpcService dailyTotalNpcService;

    @PostMapping("/inner/dailytotalnpc/selectById")
    public DailyTotalNpcEntity selectById(){
        DailyTotalNpcEntity dailyTotalNpcEntity = null;
        List<DailyTotalNpcEntity> dailyTotalNpcList = dailyTotalNpcService.selectList(new EntityWrapper<DailyTotalNpcEntity>());
        if(null != dailyTotalNpcList && dailyTotalNpcList.size() > 0) {
            dailyTotalNpcEntity = dailyTotalNpcList.get(0);
        }
        return dailyTotalNpcEntity;
    }

    @PostMapping("/inner/dailytotalnpc/update")
    public Boolean update(@RequestBody DailyTotalNpcEntity daily){
        return dailyTotalNpcService.update(daily,new EntityWrapper<DailyTotalNpcEntity>());
    }

    @GetMapping("inner/dailytotalnpc/list")
    public List<DailyTotalNpcEntity> npcList(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(currentTime);
        return dailyTotalNpcService.npcList(today);
    }

    @PostMapping(value = "inner/dailytotalnpc/add",consumes = "application/json")
    public Integer npcAdd(@RequestBody DailyTotalNpcEntity entity){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(currentTime);
        List<DailyTotalNpcEntity> list = dailyTotalNpcService.npcList(today);
        if(list.size() < 7){
            dailyTotalNpcService.insert(entity);
            return 1;
        }
        return 0;
    }
}

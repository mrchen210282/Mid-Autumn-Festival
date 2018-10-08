package cn.bitflash.controller;

import cn.bitflash.entity.UserHlbTradeHistoryEntity;
import cn.bitflash.entity.UserNpcTradeHistoryEntity;
import cn.bitflash.service.UserHlbTradeHistoryService;
import cn.bitflash.service.UserNpcTradeHistoryService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserHlbHistoryController {

    @Autowired
    private UserHlbTradeHistoryService userHlbTradeHistoryService;

    @PostMapping("/inner/userHlbhistory/insert")
    public Boolean insertUserNpcEntity(@RequestBody UserHlbTradeHistoryEntity hlbTradeHistoryEntity){
        return userHlbTradeHistoryService.insert(hlbTradeHistoryEntity);
    }

    @PostMapping("/inner/userHlbhistory/selectHistorys")
    public List<UserHlbTradeHistoryEntity> selectHistorys(@RequestParam("id")String id){
        return userHlbTradeHistoryService.selectList(new EntityWrapper<UserHlbTradeHistoryEntity>().eq("uid",id));
    }
}

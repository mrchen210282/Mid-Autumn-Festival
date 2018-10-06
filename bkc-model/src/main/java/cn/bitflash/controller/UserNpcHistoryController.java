package cn.bitflash.controller;

import cn.bitflash.entity.UserNpcTradeHistoryEntity;
import cn.bitflash.service.UserNpcTradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserNpcHistoryController {

    @Autowired
    private UserNpcTradeHistoryService userNpcTradeHistoryService;

    @PostMapping("/inner/usernpchistory/insert")
    public Boolean insertUserNpcEntity(@RequestBody UserNpcTradeHistoryEntity npcTradeHistoryEntity){
        return userNpcTradeHistoryService.insert(npcTradeHistoryEntity);
    }
}

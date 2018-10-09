package cn.bitflash.controller;

import cn.bitflash.entity.UserNpcTradeHistoryEntity;
import cn.bitflash.service.UserNpcTradeHistoryService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserNpcHistoryController {

    @Autowired
    private UserNpcTradeHistoryService userNpcTradeHistoryService;

    @PostMapping("/inner/usernpchistory/insert")
    public Boolean insertUserNpcEntity(@RequestBody UserNpcTradeHistoryEntity npcTradeHistoryEntity){
        return userNpcTradeHistoryService.insert(npcTradeHistoryEntity);
    }

    @PostMapping("/inner/usernpchistory/selectNpchistory")
    public List<UserNpcTradeHistoryEntity> selectNpchistory(@RequestParam("uid")String uid){
        return  userNpcTradeHistoryService.selectList(new EntityWrapper<UserNpcTradeHistoryEntity>().eq("uid",uid));
    }

}

package cn.bitflash.controller;

import cn.bitflash.entity.GameAccountHistoryEntity;
import cn.bitflash.entity.PriceLinechartEntity;
import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.service.GameAccountHistoryService;
import cn.bitflash.service.PriceLinechartService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameAccountHistoryController {


    @Autowired
    private GameAccountHistoryService gameAccountHistoryService;

    /**
     * insertGameAccount
     *
     * @return
     */
    @PostMapping("/inner/gameAccountHistory/insertGameAccount")
    public void insertGameAccount(@RequestBody JSONObject json) {
        GameAccountHistoryEntity entity = (GameAccountHistoryEntity) JSONObject.parseObject(json.toString(), GameAccountHistoryEntity.class);
        gameAccountHistoryService.insert(entity);
    }
}

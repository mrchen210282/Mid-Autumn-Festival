package cn.bitflash.controller;

import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.entity.UserMarketTradeHistoryEntity;
import cn.bitflash.service.UserMarketTradeHistoryService;
import cn.bitflash.service.UserMarketTradeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMarketTradeHistoryController {

    @Autowired
    private UserMarketTradeHistoryService userMarketTradeHistoryService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userMarketTradeHistory/selectById")
    public UserMarketTradeHistoryEntity selectById(@RequestParam("id") String id) {
        return userMarketTradeHistoryService.selectById(id);
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userMarketTradeHistory/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserMarketTradeHistoryEntity entity = (UserMarketTradeHistoryEntity) JSONObject.parseObject(json.toString(), UserMarketTradeHistoryEntity.class);
        return userMarketTradeHistoryService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userMarketTradeHistory/insertUserMarketTradeHistory")
    public boolean insertUserMarketTradeHistory(@RequestBody JSONObject json) throws Exception {
        UserMarketTradeHistoryEntity entity = (UserMarketTradeHistoryEntity) JSONObject.parseObject(json.toString(), UserMarketTradeHistoryEntity.class);
        return userMarketTradeHistoryService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userMarketTrade/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userMarketTradeHistoryService.deleteById(id);
    }

}

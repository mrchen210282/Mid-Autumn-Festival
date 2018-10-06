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
        UserMarketTradeHistoryEntity entity = userMarketTradeHistoryService.selectById(id);
        return entity;
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
    @PostMapping("/inner/userMarketTradeHistory/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
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

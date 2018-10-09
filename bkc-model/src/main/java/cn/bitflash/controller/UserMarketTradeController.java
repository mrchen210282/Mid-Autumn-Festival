package cn.bitflash.controller;

import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.service.UserMarketTradeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class UserMarketTradeController {

    @Autowired
    private UserMarketTradeService userMarketTradeService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userMarketTrade/selectById")
    public UserMarketTradeEntity selectById(@RequestParam("id") String id) {
        UserMarketTradeEntity entity = userMarketTradeService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userMarketTrade/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserMarketTradeEntity entity = (UserMarketTradeEntity) JSONObject.parseObject(json.toString(), UserMarketTradeEntity.class);
        return userMarketTradeService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userMarketTrade/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserMarketTradeEntity entity = (UserMarketTradeEntity) JSONObject.parseObject(json.toString(), UserMarketTradeEntity.class);
        return userMarketTradeService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userMarketTrade/deleteUserMarketTradeById")
    public boolean deleteUserMarketTradeById(@RequestParam("id") String id) throws Exception {
        return userMarketTradeService.deleteById(id);
    }
}

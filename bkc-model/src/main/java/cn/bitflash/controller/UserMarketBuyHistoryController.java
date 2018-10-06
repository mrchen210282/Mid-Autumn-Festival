package cn.bitflash.controller;

import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketBuyHistoryEntity;
import cn.bitflash.service.UserMarketBuyHistoryService;
import cn.bitflash.service.UserMarketBuyService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserMarketBuyHistoryController {

    @Autowired
    private UserMarketBuyHistoryService userMarketBuyHistoryService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userMarketBuyHistory/selectById")
    public UserMarketBuyHistoryEntity selectById(@RequestParam("id") String id) {
        UserMarketBuyHistoryEntity entity = userMarketBuyHistoryService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userMarketBuy/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserMarketBuyHistoryEntity entity = (UserMarketBuyHistoryEntity) JSONObject.parseObject(json.toString(), UserMarketBuyHistoryEntity.class);
        return userMarketBuyHistoryService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userMarketBuyHistory/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserMarketBuyHistoryEntity entity = (UserMarketBuyHistoryEntity) JSONObject.parseObject(json.toString(), UserMarketBuyHistoryEntity.class);
        return userMarketBuyHistoryService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userMarketBuyHistory/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userMarketBuyHistoryService.deleteById(id);
    }

}

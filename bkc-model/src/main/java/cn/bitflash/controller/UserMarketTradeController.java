package cn.bitflash.controller;

import cn.bitflash.bean.OrderListBean;
import cn.bitflash.bean.TradeListBean;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.service.UserMarketTradeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
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
    @PostMapping("/inner/userMarketTrade/insertOrUpdateUserMarketTrade")
    public boolean insertOrUpdateUserMarketTrade(@RequestBody JSONObject json) throws Exception {
        UserMarketTradeEntity entity = (UserMarketTradeEntity) JSONObject.parseObject(json.toString(), UserMarketTradeEntity.class);
        return userMarketTradeService.insertOrUpdate(entity);
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

    @PostMapping("/inner/userMarketTrade/selectOrderTrade")
    public List<OrderListBean> selectOrderTrade(@RequestParam Map<String,Object> map) throws Exception {
        return userMarketTradeService.selectOrderTrade(map);
    }

    @PostMapping("/inner/userMarketTrade/selectOrderCount")
    public Integer selectOrderCount(@RequestParam Map<String,Object> map) {
        return userMarketTradeService.selectOrderCount(map);
    }

    @PostMapping("/inner/userMarketTrade/tradeList")
    public List<TradeListBean> tradeList(@RequestParam Map<String,Object> map) {
        return userMarketTradeService.tradeList(map);
    }

    @PostMapping("/inner/userMarketTrade/tradeListCount")
    Integer tradeListCount(@RequestParam Map<String,Object> map) {
        return userMarketTradeService.tradeListCount(map);
    }


    @PostMapping("/inner/userMarketTrade/queryDetail")
    Integer queryDetail(@RequestParam Map<String,Object> map) {
        return userMarketTradeService.tradeListCount(map);
    }
}

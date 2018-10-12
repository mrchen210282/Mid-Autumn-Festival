package cn.bitflash.controller;

import cn.bitflash.bean.AllUserTradeBean;
import cn.bitflash.bean.OrderListBean;
import cn.bitflash.bean.TradeListBean;
import cn.bitflash.bean.UserTradeDetail;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.service.UserMarketTradeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserMarketTradeController {

    @Autowired
    private UserMarketTradeService userMarketTradeService;

    /**
     * selectUserMarketTradeById
     *
     * @return
     */
    @PostMapping("/inner/userMarketTrade/selectById")
    public UserMarketTradeEntity selectUserMarketTradeById(@RequestParam("id") String id) {
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
    AllUserTradeBean queryDetail(@RequestParam Map<String,Object> map) {
        return userMarketTradeService.queryDetail(map);
    }

    @PostMapping("/inner/userMarketTrade/selectTradeByIdAndState")
    public UserMarketTradeEntity selectTradeByIdAndState(@RequestParam("id") String id, @RequestParam("state") String state) {
        UserMarketTradeEntity userMarketTradeEntity = userMarketTradeService.selectOne(new EntityWrapper<UserMarketTradeEntity>().eq("id",id).eq("state",state));
        return userMarketTradeEntity;
    }

    @PostMapping("/inner/userMarketTrade/selectTradePrompt")
    public int selectTradePrompt(@RequestParam("uid") String uid) {
        return userMarketTradeService.selectTradePrompt(uid);
    }


    @PostMapping("/inner/userMarketTrade/responseTrade")
    public Map<String,Object> responseTrade(@RequestParam("uid") String uid) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uid",uid);
        Map<String,Object> returnMap = userMarketTradeService.responseTrade(map);
        return returnMap;
    }

    @PostMapping("/inner/userMarketTrade/selectDetail")
    public UserTradeDetail selectDetail(@RequestParam Map<String,Object> map) {
        UserTradeDetail userTradeDetail = userMarketTradeService.selectDetail(map);
        return userTradeDetail;
    }

    @PostMapping("/inner/userMarketTrade/cancelOrder")
    public void cancelOrder(@RequestParam Map<String,Object> map) {
        userMarketTradeService.cancelOrder(map);
    }

}


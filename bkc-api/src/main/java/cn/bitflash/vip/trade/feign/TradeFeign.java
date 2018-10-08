package cn.bitflash.vip.trade.feign;

import cn.bitflash.entity.*;
import cn.bitflash.vip.trade.entity.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "bkc-model")
public interface TradeFeign {

    /**
     * user_complaint 表
     */
    @PostMapping("/inner/userComplaint/selectById")
    UserComplaintEntity selectUserCompById(@RequestParam("id") String orderId);

    @PostMapping("/inner/userComplaint/updateById")
    void insertUserComplaint(@RequestBody UserComplaintEntity complaint);

    /**
     * user_trade 表
     */
    @PostMapping("/inner/userTrade/insertOrUpdateTrade")
    boolean insertOrUpdateTrade(@RequestBody UserMarketTradeEntity trade);

    @PostMapping("/inner/userTrade/updateById")
    void updateTrade(@RequestBody UserMarketTradeEntity trade);

    @PostMapping("/inner/userTrade/deleteById")
    void deleteTrade(@RequestParam("id") String id);

    @PostMapping("/inner/userMarketTrade/selectById")
    UserMarketTradeEntity selectTradeById(@RequestParam("id") String id);

    @PostMapping("/inner/userTrade/selectTradeByIdAndState")
    UserMarketTradeEntity selectTradeByIdAndState(@RequestParam("id") String id, @RequestParam("state") String state);

    @PostMapping("")
    UserTradeDetail selectDetail(@RequestParam("id") String id);

    @PostMapping("")
    AllUserTradeBean queryDetail(@RequestParam("id") String id);

    @PostMapping("")
    List<TradeListBean> selectTradeHistory(@RequestBody Map<String, Object> param);

    @PostMapping("")
    List<TradeListBean> tradeList(@RequestParam("uid") String uid, @RequestParam("pageNum") String pageNum, @RequestParam("pageTotal") String pageTotal);

    @PostMapping("")
    List<UserMarketTradeEntity> selectTradeByState(@RequestParam("state") String state);

    @PostMapping("")
    List<OrderListBean> selectOrderTrade(@RequestParam("uid") String uid, @RequestParam("pageNum") String pageNum, @RequestParam("pageTotal") String pageTotal);

    @PostMapping("")
    Integer selectTradeCount(@RequestParam("uid") String uid, @RequestParam("pageNum") String pageNum, @RequestParam("pageTotal") String pageTotal);

    @PostMapping("")
    Integer tradeListCount(@RequestParam("uid") String uid, @RequestParam("pageNum") String pageNum, @RequestParam("pageTotal") String pageTotal);

    /**
     * user_trade_history 表
     */
    @PostMapping("/inner/userTradeHistory/selectById")
    UserMarketTradeHistoryEntity selectTradeHistoryById(@RequestParam("id") String orderId);

    @PostMapping("")
    void updateUserTradeHistory(@RequestBody UserMarketTradeHistoryEntity history);

    @PostMapping("")
    void delUserTradeById(@RequestParam("orderId") String orderId);

    @PostMapping("")
    void insertUserTradeHistory(@RequestBody UserMarketTradeHistoryEntity historyEntity);

    /**
     * user_trade_config 表
     */
    @PostMapping("")
    UserTradeConfigEntity selectTradeConfigById(@RequestParam("id") Integer id);

    @PostMapping("")
    void deleteTradePoundageById(@RequestParam("id") String id);

    @PostMapping("")
    void insertTradePoundage(@RequestBody TradePoundageEntity poundageEntity);

    /**
     */
    @PostMapping("/inner/userSecret/selectById")
    UserSecretEntity selectById(@RequestParam("uid") String uid);

    /**
     * selectUserBrokerageById
     *
     * @return
     */
    @PostMapping("/inner/userBroker/updateUserBrokerage")
    boolean updateUserBrokerage(@RequestBody UserBrokerageEntity userBrokerageEntity);

    /**
     * user_assets_npc 表
     */
    @PostMapping("")
    UserAssetsNpcEntity selectAccountByUid(@RequestParam("uid") String uid);

    @PostMapping("/inner/userAssetsNpc/updateUserAssetsNpc")
    public Boolean updateUserAssetsNpc(@RequestBody UserAssetsNpcEntity npc);

    @PostMapping("/inner/tradePoundage/deleteById")
    boolean deleteById(@RequestParam("id") String id);


    /**
     * deleteUserMarketTradeById
     *
     * @return
     */
    @PostMapping("/inner/userMarketTrade/deleteUserMarketTradeById")
    public boolean deleteUserMarketTradeById(@RequestParam("id") String id);

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userMarketTradeHistory/insertUserMarketTradeHistory")
    public boolean insertUserMarketTradeHistory(@RequestBody UserMarketTradeHistoryEntity userMarketTradeHistoryEntity);

    /**
     * selectTradePoundageById
     *
     * @return
     */
    @PostMapping("/inner/tradePoundage/selectTradePoundageById")
    TradePoundageEntity selectTradePoundageById(@RequestParam("id") String id);

    /**
     * selectUserBrokerageById
     *
     * @return
     */
    @PostMapping("/inner/userBroker/selectUserBrokerageById")
    public UserBrokerageEntity selectUserBrokerageById(@RequestParam("id") Integer id);


    @PostMapping("")
    Map<String, Object> responseTrade(@RequestParam("uid") String uid);

    /**
     * user_brokerage 表
     */
//    @PostMapping("")
//    UserBrokerageEntity selectUserBrokerageById(@RequestParam("id") Integer id);
//
//    @PostMapping("")
//    void updateUserBrokerage(@RequestBody UserBrokerageEntity brokerage);

    /**
     * user_getui_cid 表
     */
    @PostMapping("")
    UserGTCidEntity selectGT(@RequestParam("uid") String uid);

    /**
     * platform_config
     */
    @PostMapping("")
    String getVal(@RequestParam("key") String key);


}

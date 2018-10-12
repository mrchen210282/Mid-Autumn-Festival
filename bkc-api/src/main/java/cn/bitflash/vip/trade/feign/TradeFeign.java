package cn.bitflash.vip.trade.feign;

import cn.bitflash.entity.*;
import cn.bitflash.vip.trade.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "bkc-model")
public interface TradeFeign {

    /**
     * user_complaint 表
     */
    @PostMapping("/inner/userComplaint/selectById")
    UserComplaintEntity selectUserCompById(@RequestParam("id") String orderId);

    @PostMapping("/inner/userComplaint/insert")
    void insertUserComplaint(@RequestBody UserComplaintEntity complaint);

    /**
     * user_market_trade 表
     */
    @PostMapping("/inner/userMarketTrade/insertOrUpdateUserMarketTrade")
    boolean insertOrUpdateUserMarketTrade(@RequestBody UserMarketTradeEntity trade);


    @PostMapping("/inner/userMarketTrade/updateById")
    void updateTrade(@RequestBody UserMarketTradeEntity trade);

    @PostMapping("/inner/userTrade/deleteById")
    void deleteTrade(@RequestParam("id") String id);

    @PostMapping("/inner/userMarketTrade/selectTradeByIdAndState")
    UserMarketTradeEntity selectTradeByIdAndState(@RequestParam("id") String id, @RequestParam("state") String state);

    @PostMapping("/inner/userMarketTrade/selectDetail")
    UserTradeDetail selectDetail(@RequestParam Map<String, Object> map);

    @PostMapping("/inner/userMarketTrade/queryDetail")
    AllUserTradeBean queryDetail(@RequestParam Map<String, Object> map);

    @PostMapping("")
    List<TradeListBean> selectTradeHistory(@RequestBody Map<String, Object> param);

    @PostMapping("/inner/userMarketTrade/tradeList")
    List<TradeListBean> tradeList(@RequestParam Map<String, Object> map);

    @PostMapping("/inner/userMarketTrade/tradeListCount")
    Integer tradeListCount(@RequestParam Map<String, Object> map);

    @PostMapping("")
    List<UserMarketTradeEntity> selectTradeByState(@RequestParam("state") String state);

    @PostMapping("/inner/userMarketTrade/selectOrderTrade")
    List<OrderListBean> selectOrderTrade(@RequestParam Map<String, Object> map);

    @PostMapping("/inner/userMarketTrade/selectOrderCount")
    Integer selectOrderCount(@RequestParam Map<String, Object> map);


    /**
     * user_trade_history 表
     */
    @PostMapping("/inner/userTradeHistory/selectById")
    UserMarketTradeHistoryEntity selectTradeHistoryById(@RequestParam("id") String orderId);

    @PostMapping("/inner/userLogin/selectById")
    UserSecretEntity selectUserSecretById(@RequestParam("id") String id);

    @PostMapping("")
    void updateUserTradeHistory(@RequestBody UserMarketTradeHistoryEntity history);

    @PostMapping("")
    void delUserTradeById(@RequestParam("orderId") String orderId);

    @PostMapping("")
    void insertUserTradeHistory(@RequestBody UserMarketTradeHistoryEntity historyEntity);

    /**
     * user_trade_config 表
     */
    @PostMapping("/inner/userMarketConfig/selectById")
    UserTradeConfigEntity selectUserMarketConfigById(@RequestParam("id") Integer id);

    @PostMapping("/inner/tradePoundage/insertTradePoundage")
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
    @PostMapping("/inner/userAssetsNpc/selectById")
    UserAssetsNpcEntity selectUserAssetsNpcById(@RequestParam("id") String uid);

    @PostMapping("/inner/userAssetsNpc/update")
    public Boolean updateUserAssetsNpc(@RequestBody UserAssetsNpcEntity npc);

    @PostMapping("/inner/tradePoundage/deleteTradePoundageById")
    boolean deleteTradePoundageById(@RequestParam("id") String id);


    /**
     * deleteUserMarketTradeById
     *
     * @returnselectById
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
    @PostMapping("/inner/tradePoundage/selectById")
    TradePoundageEntity selectTradePoundageById(@RequestParam("id") String id);

    /**
     * selectUserBrokerageById
     *
     * @return
     */
    @PostMapping("/inner/userBroker/selectById")
    public UserBrokerageEntity selectUserBrokerageById(@RequestParam("id") Integer id);

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
    @PostMapping("/inner/userGetui/selectById")
    UserGetuiEntity selectGT(@RequestParam("id") String uid);

    /**
     * system_param 表
     */
    @GetMapping("/inner/systemParam/getVal/{key}")
    String getVal(@PathVariable("key") String key);


    @PostMapping("/inner/userMarketTrade/selectById")
    public UserMarketTradeEntity selectUserMarketTradeById(@RequestParam("id") String id);

    @PostMapping("/inner/userMarketTrade/responseTrade")
    Map<String, Object> responseTrade(@RequestParam("uid") String uid);

    @PostMapping("/inner/userMarketTrade/cancelOrder")
    void cancelOrder(@RequestParam Map<String, Object> map);
}

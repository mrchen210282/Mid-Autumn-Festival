package cn.bitflash.vip.order.feign;

import cn.bitflash.entity.*;
import cn.bitflash.vip.buy.Bean.UserBuyBean;
import cn.bitflash.vip.order.entity.OrderTradeDetail;
import cn.bitflash.vip.order.entity.UserComplaintBean;
import cn.bitflash.vip.order.entity.UserSuccessBean;
import cn.bitflash.vip.order.entity.UserTradeJoinBuyEntity;
import cn.bitflash.vip.trade.entity.UserTradeConfigEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "bkc-model")
public interface OrderFeign {

    /**
     * userComplaint
     */
    @PostMapping("/inner/userComplaint/selectAppealList")
    List<UserBuyBean> selectAppealList(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);

    @PostMapping("/inner/userComplaint/selectAppealCount")
    Integer selectAppealCount(@RequestParam("uid") String uid);

    @PostMapping("/inner/userComplaint/getComplaintMessage")
    UserComplaintBean getComplaintMessage(@RequestParam("id") String id);

    /**
     * userMarketTrade
     */
    @PostMapping("/inner/userMarketTrade/selectById")
    UserMarketTradeEntity selectTradeById(@RequestParam("id") String id);

    /**
     * userMarketBuy
     */
    @PostMapping("/inner/userMarketBuy/selectById")
    UserMarketBuyEntity selectUserBuyById(@RequestParam("id") String id);

    /**
     * user_trade_config
     */
    @PostMapping("/inner/userMarketConfig/selectById")
    UserTradeConfigEntity selectTradeConfigById(@RequestParam("id") int id);

    /**
     * user_success
     */
    @PostMapping("/inner/userSuccess/selectSuccessList")
    List<UserSuccessBean> selectSuccessList(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);

    @PostMapping("/inner/userSuccess/selectSuccessCount")
    Integer selectSuccessCount(@RequestParam("uid") String uid);

    @PostMapping("/inner/userSuccess/getSuccessMessage")
    UserSuccessBean getSuccessMessage(@RequestParam("id") String uid,@RequestParam("state") String state);

    /**
     * user_trade_history
     */
    @PostMapping("/inner/userMarketTradeHistory/selectById")
    UserMarketTradeHistoryEntity selectTradeHistoryById(@RequestParam("id") String id);
    /**
     * user_buy_history
     */
    @PostMapping("/inner/userMarketBuyHistory/selectById")
    UserMarketBuyHistoryEntity selectBuyHistoryById(@RequestParam("id") String id);

    /**
     * Prompt
     */
    @PostMapping("/inner/userMarketTrade/selectTradePrompt")
    int selectTradePrompt(@RequestParam("uid") String uid);
    @PostMapping("/inner/userMarketBuy/selectBuyPrompt")
    int selectBuyPrompt(@RequestParam("uid") String uid);
    @PostMapping("/inner/userMarketBuyHistory/selectAppealPrompt")
    int selectAppealPrompt(@RequestParam("uid") String uid);
    @PostMapping("/inner/userMarketBuyHistory/selectSuccessPrompt")
    int selectSuccessPrompt(@RequestParam("uid") String uid);

}

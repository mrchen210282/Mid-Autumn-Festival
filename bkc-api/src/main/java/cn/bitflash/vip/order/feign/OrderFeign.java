package cn.bitflash.vip.order.feign;

import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.vip.buy.Bean.UserBuyBean;
import cn.bitflash.vip.order.entity.OrderTradeDetail;
import cn.bitflash.vip.order.entity.UserComplaintBean;
import cn.bitflash.vip.order.entity.UserTradeJoinBuyEntity;
import cn.bitflash.vip.trade.entity.UserTradeConfigEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "bkc-model")
public interface OrderFeign {

    @PostMapping("/inner/userComplaint/selectAppealList")
    List<UserBuyBean> selectAppealList(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);

    @PostMapping("/inner/userComplaint/selectAppealCount")
    Integer selectAppealCount(@RequestParam("uid") String uid);

    @PostMapping("/inner/userComplaint/getComplaintMessage")
    UserComplaintBean getComplaintMessage(@RequestParam("id") String id);

    /**
     * user_account表
     */
    @PostMapping("/inner/userMarketBuy/selectById")
    UserAssetsNpcEntity selectAccountByUid(@RequestParam("uid") String uid);

    /**
     * user_trade 表
     */
    @PostMapping("/inner/userMarketBuy/selectById")
    UserMarketTradeEntity selectTradeById(@RequestParam("id") String id);

    /**
     * user_buy 表
     */
    @PostMapping("/inner/userMarketBuy/selectById")
    UserMarketBuyEntity selectUserBuyById(@RequestParam("id") String id);

    /**
     * user_trade_config 表
     */
    @PostMapping("/inner/userMarketConfig/selectById")
    UserTradeConfigEntity selectTradeConfigById(@RequestParam("id") int id);

    /**
     * user_trade_history
     */
    @PostMapping("")
    List<UserTradeJoinBuyEntity> selectFinishOrder(@RequestParam("uid") String uid,
                                                   @RequestParam("pageNum") String pageNum,
                                                   @RequestParam("pageTotal") String pageTotal);

    @PostMapping("")
    Integer selectFinishOrderCount(@RequestParam("uid") String uid,
                                   @RequestParam("pageNum") String pageNum,
                                   @RequestParam("pageTotal") String pageTotal);

    @PostMapping("")
    OrderTradeDetail checkSuccess(@RequestParam("id") String id);
}

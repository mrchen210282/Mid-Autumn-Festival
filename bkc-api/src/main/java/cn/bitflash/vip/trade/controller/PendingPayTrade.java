package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.R;
import cn.bitflash.utils.RedisUtils;
import cn.bitflash.vip.trade.feign.TradeCommon;
import cn.bitflash.vip.trade.feign.TradeFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trade")
@Api(value = "交易流程", tags = "付款，取消订单")
public class PendingPayTrade {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TradeFeign tradeFeign;

    @Autowired
    private RedisUtils redisUtils;

    @Login
    @PostMapping("payTrade")
    @ApiOperation(value = "我已付款")
    public R payTrade(@ApiParam @RequestParam String orderId, @RequestAttribute("uid") String uid) {
        // 更新为已购买
        UserMarketTradeEntity userTradeEntity = tradeFeign.selectTradeById(orderId);
        if (!userTradeEntity.getState().equals(TradeCommon.STATE_LOCK)) {
            return R.error("订单状态有误，请稍后再试");
        }
        userTradeEntity.setState(TradeCommon.STATE_CONFIRM);
        tradeFeign.updateTrade(userTradeEntity);
        logger.info("方法payTrade(我已付款)，订单号:" + orderId);
        return R.ok();
    }

    @Login
    @PostMapping("cancelOrder")
    @ApiOperation(value = "取消订单(买家操作)")
    public R cancelOrder(@ApiParam @RequestParam String orderId) {
        //删除锁定状态
        redisUtils.delete(orderId);
        // 更新状态为交易中
        UserMarketTradeEntity userMarketTradeEntity = new UserMarketTradeEntity();
        userMarketTradeEntity.setId(orderId);
        userMarketTradeEntity.setState(TradeCommon.STATE_SELL);
        tradeFeign.insertOrUpdateUserMarketTrade(userMarketTradeEntity);
        logger.info("取消订单号:" + orderId);
        return R.ok();
    }
}

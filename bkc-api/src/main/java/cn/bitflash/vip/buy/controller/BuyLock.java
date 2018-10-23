package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.utils.R;
import cn.bitflash.utils.RedisUtils;
import cn.bitflash.vip.buy.feign.BuyFeign;
import cn.bitflash.vip.trade.feign.TradeCommon;
import cn.bitflash.vip.trade.feign.TradeFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.bitflash.vip.buy.controller.BuyCommon.ORDER_STATE_STEP1;

@RestController
@RequestMapping("/buy")
@Api(value = "订单验证", tags = "订单验证，解锁")
public class BuyLock {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private BuyFeign feign;

    @Login
    @PostMapping("provingState")
    @ApiOperation(value = "验证订单是否锁定")
    public R provingState(@ApiParam @RequestParam String orderId, @RequestAttribute("uid") String uid) {
        String[] str = redisUtils.get(orderId, String[].class);
        if (str == null || str.length == 0) {
            return R.ok().put("code", 200);
        } else if (str[1].equals(uid)) {
            return R.ok().put("code", 400);
        }
        return R.error("订单已锁定");
    }

    @Login
    @PostMapping("updateBuyState")
    @ApiOperation(value = "解锁失效的订单")
    public R updateTradeState() {
        String state = ORDER_STATE_STEP1;
        List<UserMarketBuyEntity> trades = feign.selectBuyState(state);
            trades.stream().forEach((t) -> {
            String[] str = redisUtils.get(t.getId(), String[].class);
            if (str == null || str.length == 0) {
                if (t.getState().equals(TradeCommon.STATE_LOCK)) {
                    UserMarketBuyEntity userMarketBuyEntity = new UserMarketBuyEntity();
                    userMarketBuyEntity.setId(t.getId());
                    userMarketBuyEntity.setState(BuyCommon.ORDER_STATE_PUBLISH);
                    feign.updateBuyById(userMarketBuyEntity);
                }
            }
        });
        return R.ok();
    }
}

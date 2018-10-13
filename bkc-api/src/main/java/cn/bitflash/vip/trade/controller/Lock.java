package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.R;
import cn.bitflash.utils.RedisUtils;
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

@RestController
@RequestMapping("trade")
@Api(value = "订单验证", tags = "订单验证，解锁")
public class Lock {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private TradeFeign tradeFeign;

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
    @PostMapping("updateTradeState")
    @ApiOperation(value = "解锁失效的订单")
    public R updateTradeState() {
        String state = "5";
        List<UserMarketTradeEntity> trades = tradeFeign.selectTradeByState(state);
        trades.stream().forEach((t) -> {
            String[] str = redisUtils.get(t.getId(), String[].class);
            if (str == null || str.length == 0) {
                if (t.getState().equals(TradeCommon.STATE_LOCK)) {
//                    t.setState(TradeCommon.STATE_SELL);
//                    tradeFeign.updateTrade(t);
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("id",t.getId());
                    map.put("state",TradeCommon.STATE_SELL);
                    tradeFeign.cancelOrder(map);
                }
            }
        });
        return R.ok();
    }
}

package cn.bitflash.vip.order.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketBuyHistoryEntity;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.entity.UserMarketTradeHistoryEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.buy.Bean.UserBuyBean;
import cn.bitflash.vip.order.entity.OrderTradeDetail;
import cn.bitflash.vip.order.entity.UserComplaintBean;
import cn.bitflash.vip.order.entity.UserSuccessBean;
import cn.bitflash.vip.order.entity.UserTradeJoinBuyEntity;
import cn.bitflash.vip.order.feign.OrderFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
@Api("订单完成列表")
public class Success {

    @Autowired
    private OrderUtil orderUtil;

    @Autowired
    private OrderFeign orderFeign;

    @Login
    @PostMapping("/successList")
    @ApiOperation("查询已完成订单")
    public R selectFinishOrder(@RequestAttribute("uid") String uid, @ApiParam @RequestParam("pageNum") String pageNum) {
        List<UserSuccessBean> list = orderFeign.selectSuccessList(uid, Integer.valueOf(pageNum));
        if (list == null || list.size() < 0) {
            return R.error("暂时没有完成信息");
        }
        Integer count = orderFeign.selectSuccessCount(uid);
        return R.ok().put("list", list).put("count", count);
    }

    @Login
    @PostMapping("/successCheck")
    @ApiOperation("查看买入订单明细")
    public R viewSuccess(@ApiParam("订单id") @RequestParam("id") String id, @RequestParam("state") String state, @RequestAttribute("uid") String uid) {
        String name = null;
        String mobile = null;

        UserSuccessBean userSuccessBean = orderFeign.getSuccessMessage(id, state);
        //判定订单不存在
        if (userSuccessBean == null) {
            return R.ok().put("code", "订单不存在");
        }
        if (uid.equals(userSuccessBean.getPurchaseUid())) {
            name = userSuccessBean.getSellNickname();
            mobile = userSuccessBean.getSellMobile();

        } else if (uid.equals(userSuccessBean.getSellUid())) {
            name = userSuccessBean.getPurchaseNickname();
            mobile = userSuccessBean.getPurMobile();
        }

        Map<String, Float> map = orderUtil.poundage(id, state, "SUCCESS");
        return R.ok().put("orderId", id).put("name", name).put("mobile", mobile).put("map", map);
    }
}
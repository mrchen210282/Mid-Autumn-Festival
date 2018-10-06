package cn.bitflash.vip.order.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.utils.R;
import cn.bitflash.vip.order.entity.OrderTradeDetail;
import cn.bitflash.vip.order.entity.UserTradeJoinBuyEntity;
import cn.bitflash.vip.order.feign.OrderFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("order")
@Api("订单完成列表")
public class Success {

    @Autowired
    private OrderFeign orderFeign;

    @Login
    @PostMapping("selectFinishOrder")
    @ApiOperation("查询已完成订单")
    public R selectFinishOrder(@RequestAttribute("uid") String uid, @ApiParam @RequestParam String pageNum) {
        List<UserTradeJoinBuyEntity> list = orderFeign.selectFinishOrder(uid, pageNum, "6");
        Integer count = orderFeign.selectFinishOrderCount(uid, pageNum, "6");
        return R.ok().put("list", list).put("count", count);
    }

    @Login
    @PostMapping("viewSuccess")
    @ApiOperation("查看买入订单明细")
    public R viewSuccess(@ApiParam ("订单id")@RequestParam String id, @RequestAttribute("uid") String uid) {
        OrderTradeDetail detail = orderFeign.checkSuccess(id);
        String name = null;
        String mobile = null;
        if (uid.equals(detail.getPurUid())) {
            name = detail.getConName();
            mobile = detail.getConMobile();
        } else if (uid.equals(detail.getConUid())) {
            name = detail.getPurName();
            mobile = detail.getPurMobile();
        }

        //数量
        BigDecimal quantity = new BigDecimal(detail.getAcquantity());
        //价格
        BigDecimal price = new BigDecimal(detail.getAcprice());
        BigDecimal tradeAmount = price.multiply(quantity);
        detail.setTradeAmount(tradeAmount);
        return R.ok().put("name", name).put("mobile", mobile).put("userTradeBean", detail);
    }
}
package cn.bitflash.vip.order.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.buy.Bean.UserBuyBean;
import cn.bitflash.vip.order.entity.UserComplaintBean;
import cn.bitflash.vip.order.feign.OrderFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
@Api("申诉")
public class Remind {

    @Autowired
    private OrderUtil orderUtil;

    @Autowired
    private OrderFeign orderFeign;

    @Login
    @PostMapping("/appealList")
    @ApiOperation("申诉列表")
    public R selectAppealList(@RequestAttribute("uid") String uid, @ApiParam @RequestParam("pages") String pages) {
        List<UserBuyBean> ub = orderFeign.selectAppealList(uid, Integer.valueOf(pages));
        if (ub == null || ub.size() < 0) {
            return R.error("暂时没有申诉信息");
        }
        Integer count = orderFeign.selectAppealCount(uid);
        return R.ok().put("count", count).put("list", ub);
    }

    @Login
    @PostMapping("/appealCheck")
    @ApiOperation("申诉详情")
    public R checkAppeal(@ApiParam @RequestParam("id") String id, @RequestAttribute("uid") String uid,@RequestParam("state") String state) {
        String name = null;
        String mobile = null;

        UserComplaintBean userComplaintBean = orderFeign.getComplaintMessage(id);
        if (uid.equals(userComplaintBean.getComplaintUid())) {
            name = userComplaintBean.getContactsUname();
            mobile = userComplaintBean.getContactsMobile();

        } else if (uid.equals(userComplaintBean.getContactsUid())) {
            name = userComplaintBean.getComplaintUname();
            mobile = userComplaintBean.getComplaintMobile();
        }
        //判定订单不存在
        if (userComplaintBean == null) {
            return R.ok().put("code", "订单不存在");
        }

        Map<String, Float> map = orderUtil.poundage(id, state ,"REMIND");
        return R.ok().put("orderId", id).put("name", name).put("mobile", mobile).put("map",map);
//        return R.ok().put("orderId", id).put("name", name).put("mobile", mobile).put("totalQuantity", map.get("totalQuantity")).put("price", map.get("price")).put("buyQuantity", map.get("buyQuantity")).put("totalMoney", map.get("totalMoney"));
    }

}

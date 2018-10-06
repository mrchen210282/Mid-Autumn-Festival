package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.R;
import cn.bitflash.vip.trade.feign.Assert;
import cn.bitflash.vip.trade.feign.TradeCommon;
import cn.bitflash.vip.trade.feign.TradeFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/trade")
@Api(value = "申诉")
public class AppealTrade {

    @Autowired
    private TradeFeign tradeFeign;

    @Login
    @PostMapping("complaint")
    @ApiOperation(value = "添加申诉")
    public R complaint(@RequestAttribute("uid") String uid, @ApiParam(value = "订单id") @RequestParam String orderId,
                       @ApiParam(value = "买入/卖出0/1") @RequestParam String complaintState) {
        UserComplaintEntity userComplaintEntity = tradeFeign.selectUserCompById(orderId);
        Assert.isNotNull(userComplaintEntity, "订单已经申诉");
        //添加申诉
        userComplaintEntity = new UserComplaintEntity();
        userComplaintEntity.setComplaintState(complaintState);
        //设置交易订单状态为9申诉
        UserMarketTradeEntity userTradeEntity = tradeFeign.selectTradeById(orderId);
        //订单发布人uid
        userComplaintEntity.setComplaintUid(userTradeEntity.getPurchaseUid());
        //订单购买人uid
        userComplaintEntity.setContactsUid(userTradeEntity.getPurchaseUid());
        userComplaintEntity.setOrderId(orderId);
        userComplaintEntity.setOrderState(userTradeEntity.getState());
        userComplaintEntity.setCreateTime(new Date());
        tradeFeign.insertUserComplaint(userComplaintEntity);
        //状态更新为申诉
        userTradeEntity.setState(TradeCommon.STATE_COMPLAINT);
        tradeFeign.insertOrUpdateTrade(userTradeEntity);
        return R.ok();
    }

}

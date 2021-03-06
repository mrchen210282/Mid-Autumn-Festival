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
                       @ApiParam(value = "买入/卖出2/1") @RequestParam String complaintState) {
        //添加申诉
        UserComplaintEntity userComplaintEntity = new UserComplaintEntity();
        userComplaintEntity.setComplaintState(complaintState);
        //设置交易订单状态为9申诉
        UserMarketTradeEntity userMarketTradeEntity = tradeFeign.selectUserMarketTradeById(orderId);
        //订单发布人uid
        userComplaintEntity.setComplaintUid(userMarketTradeEntity.getUid());
        //订单购买人uid
        userComplaintEntity.setContactsUid(userMarketTradeEntity.getPurchaseUid());
        userComplaintEntity.setOrderId(orderId);
        userComplaintEntity.setOrderState(userMarketTradeEntity.getState());
        userComplaintEntity.setCreateTime(new Date());
        tradeFeign.insertUserComplaint(userComplaintEntity);
        //状态更新为申诉
        userMarketTradeEntity.setState(TradeCommon.STATE_COMPLAINT);
        tradeFeign.insertOrUpdateUserMarketTrade(userMarketTradeEntity);
        return R.ok();
    }

}

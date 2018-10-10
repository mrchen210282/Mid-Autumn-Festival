package cn.bitflash.vip.trade.controller;//package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.utils.R;
import cn.bitflash.vip.trade.feign.TradeCommon;
import cn.bitflash.vip.trade.feign.TradeFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("trade")
@Api(value = "查询列表数据")
public class ConfirmTrade {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TradeFeign tradeFeign;

    @Login
    @PostMapping("purchase")
    @ApiOperation(value = "确认购买")
    public R purchase(@RequestAttribute("uid") String uid, @ApiParam @RequestParam String orderId, @ApiParam @RequestParam String payPwd) {

        /**
         * 1.对比交易密码
         * 2.查看订单是否存在
         * 3.查询购买人信息
         * 4.系统手续费增加
         * 5.更新买家收益
         * 6.删除手续费表记录
         * 7.添加交易记录
         */
        UserSecretEntity userPayPwd = tradeFeign.selectById(uid);

        if (userPayPwd.getPayPassword().equals(payPwd)) {

            // 查询订单状态为已锁定(state:5)
            UserMarketTradeEntity trade = tradeFeign.selectTradeByIdAndState(orderId, TradeCommon.STATE_LOCK);

            Assert.isNull(trade,"订单不存在");

            // 卖出数量
            Float quantity = trade.getQuantity();
            // 购买人uid
            String pUid = trade.getPurchaseUid();
            TradePoundageEntity tradePoundageEntity = tradeFeign.selectTradePoundageById(orderId);
            Assert.isNull(tradePoundageEntity, "订单有误");

            //手续费累加
            UserBrokerageEntity userBrokerageEntity = tradeFeign.selectUserBrokerageById(1);
            BigDecimal poundage = new BigDecimal(tradePoundageEntity.getPoundage());

            BigDecimal multiplyB = userBrokerageEntity.getSellBrokerage().add(poundage);

            userBrokerageEntity.setSellBrokerage(multiplyB);
            tradeFeign.updateUserBrokerage(userBrokerageEntity);

            UserMarketTradeEntity entity = tradeFeign.selectTradeById(trade.getId());
            if(null != entity) {
                float availableAssets = entity.getQuantity();

                //查询出购买人
                UserAssetsNpcEntity userAssetsNpcEntity = tradeFeign.selectUserAssetsNpcById(entity.getPurchaseUid());
                if(null != userAssetsNpcEntity) {
                    float purchaseAvailable = userAssetsNpcEntity.getAvailableAssets() + availableAssets;
                    userAssetsNpcEntity.setAvailableAssets(purchaseAvailable);
                    tradeFeign.updateUserAssetsNpc(userAssetsNpcEntity);

                    //删除手续费
                    tradeFeign.deleteById(orderId);

                    //删除交易记录
                    tradeFeign.deleteUserMarketTradeById(orderId);

                    //添加历史记录
                    UserMarketTradeHistoryEntity userMarketTradeHistoryEntity = new UserMarketTradeHistoryEntity();
                    userMarketTradeHistoryEntity.setUserTradeId(orderId);
                    userMarketTradeHistoryEntity.setPurchaseUid(entity.getPurchaseUid());
                    userMarketTradeHistoryEntity.setPrice(entity.getPrice());
                    userMarketTradeHistoryEntity.setQuantity(trade.getQuantity());
                    userMarketTradeHistoryEntity.setOrderState(TradeCommon.STATE_PAY);
                    userMarketTradeHistoryEntity.setFinishTime(new Date());
                    userMarketTradeHistoryEntity.setCreateTime(new Date());
                    tradeFeign.insertUserMarketTradeHistory(userMarketTradeHistoryEntity);
                } else {
                    logger.info("购买人id:" + entity.getPurchaseUid() + "不存在！");
                }
            } else {
                logger.info("订单号:" + entity.getId() + "不存在！");
            }
            logger.info("购买人:" + uid + ",订单号:" + orderId);
            return R.ok();
        }
        return R.error("交易密码错误");

    }
}


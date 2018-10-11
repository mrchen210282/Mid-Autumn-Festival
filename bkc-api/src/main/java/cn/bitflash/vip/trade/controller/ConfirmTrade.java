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
        UserSecretEntity userPayPwd = tradeFeign.selectUserSecretById(uid);

        if (userPayPwd.getPayPassword().equals(payPwd)) {

            // 查询订单状态为待确认(state:6)
            UserMarketTradeEntity trade = tradeFeign.selectTradeByIdAndState(orderId, TradeCommon.STATE_CONFIRM);
            if (null != trade) {
                // 卖出数量
                Float quantity = trade.getQuantity();
                TradePoundageEntity tradePoundageEntity = tradeFeign.selectTradePoundageById(orderId);
                if(null != tradePoundageEntity) {

                    //手续费累加
                    Integer id = 1;
                    UserBrokerageEntity userBrokerageEntity = tradeFeign.selectUserBrokerageById(id);
                    BigDecimal poundage = new BigDecimal(tradePoundageEntity.getPoundage());

                    BigDecimal multiplyB = userBrokerageEntity.getSellBrokerage().add(poundage);

                    userBrokerageEntity.setSellBrokerage(multiplyB);
                    tradeFeign.updateUserBrokerage(userBrokerageEntity);

                    UserMarketTradeEntity entity = tradeFeign.selectUserMarketTradeById(trade.getId());
                    if (null != entity) {
                        float availableAssets = entity.getQuantity();

                        //查询出购买人
                        UserAssetsNpcEntity userAssetsNpcEntity = tradeFeign.selectUserAssetsNpcById(entity.getPurchaseUid());
                        if (null != userAssetsNpcEntity) {
                            float purchaseAvailable = userAssetsNpcEntity.getAvailableAssets() + availableAssets;
                            userAssetsNpcEntity.setAvailableAssets(purchaseAvailable);
                            tradeFeign.updateUserAssetsNpc(userAssetsNpcEntity);

                            //删除手续费
                            tradeFeign.deleteTradePoundageById(orderId);

                            //删除交易记录
                            tradeFeign.deleteUserMarketTradeById(orderId);

                            //添加历史记录
                            UserMarketTradeHistoryEntity userMarketTradeHistoryEntity = new UserMarketTradeHistoryEntity();
                            userMarketTradeHistoryEntity.setUserTradeId(orderId);
                            userMarketTradeHistoryEntity.setSellUid(trade.getUid());
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
                } else {
                    logger.info("订单号:" + orderId + "手续费不存在！");
                }

            } else {
                logger.info("订单号:" + orderId + "不存在！");
            }
            logger.info("购买人:" + uid + ",订单号:" + orderId);
            return R.ok();
        }
        return R.error("交易密码错误");

    }
}


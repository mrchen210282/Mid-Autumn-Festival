package cn.bitflash.vip.trade.controller;//package cn.bitflash.vip.trade.controller;
//
//import cn.bitflash.annotation.Login;
//import cn.bitflash.entities.*;
//import cn.bitflash.util.Assert;
//import cn.bitflash.util.Common;
//import cn.bitflash.util.R;
//import cn.bitflash.vip.trade.feign.TradeFeign;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//
//@RestController
//@RequestMapping("trade")
//@Api(value = "查询列表数据")
//public class ConfirmTrade {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private TradeFeign tradeFeign;
//
//    @Login
//    @PostMapping("purchase")
//    @ApiOperation(value = "确认购买")
//    public R purchase(@RequestAttribute("uid") String uid, @ApiParam @RequestParam String orderId, @ApiParam @RequestParam String payPwd) {
//
//        /**
//         * 1.对比交易密码
//         * 2.查看订单是否存在
//         * 3.查询购买人信息
//         * 4.系统手续费增加
//         * 5.更新买家收益
//         * 6.删除手续费表记录
//         */
//        UserPayPwdEntity userPayPwd = tradeFeign.selectUserPwdByUid(uid);
//        if (userPayPwd.getPayPassword().equals(payPwd)) {
//
//            // 查询订单状态为已锁定(state:5)
//            UserTradeEntity trade = tradeFeign.selectTradeByIdAndState(orderId, Common.STATE_LOCK);
//            Assert.isNull(trade, "订单不存在");
//            // 订单状态变为已付款
//            trade.setState(Common.STATE_PAY);
//            tradeFeign.insertOrUpdateTrade(trade);
//            // 卖出数量
//            BigDecimal quantity = trade.getQuantity();
//            // 购买人uid
//            String pUid = trade.getPurchaseUid();
//            UserDigitalIncome userAccount = tradeFeign.selectAccountByUid(pUid);
//            TradePoundageEntity tradePoundageEntity = tradeFeign.selectTradePoundageById(orderId);
//            Assert.isNull(tradePoundageEntity, "订单有误");
//            // 计算手续费
//            // 手续费=卖出数量*0.05
//            UserBrokerageEntity userBrokerageEntity = tradeFeign.selectUserBrokerageById(1);
//            BigDecimal multiplyB = userBrokerageEntity.getSellBrokerage().add(tradePoundageEntity.getPoundage());
//            userBrokerageEntity.setSellBrokerage(multiplyB);
//            tradeFeign.updateUserBrokerage(userBrokerageEntity);
//
//            BigDecimal regulateIncome = userAccount.getRegulateIncome().add(quantity);
//            userAccount.setRegulateIncome(regulateIncome);
//            BigDecimal availableAssets = userAccount.getAvailableAssets().add(quantity);
//            userAccount.setAvailableAssets(availableAssets);
//            userAccount.setUid(pUid);
//            tradeFeign.updateUserAccount(userAccount);
//
//            //删除手续费记录
//            tradeFeign.deleteTradePoundageById(orderId);
//            logger.info("购买人:" + uid + ",订单号:" + orderId);
//            return R.ok();
//        }
//        return R.error("交易密码错误");
//
//    }
//
//
//}

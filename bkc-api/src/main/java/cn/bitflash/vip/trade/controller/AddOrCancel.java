package cn.bitflash.vip.trade.controller;//package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.TradePoundageEntity;
import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.R;
import cn.bitflash.utils.RedisUtils;
import cn.bitflash.vip.trade.entity.UserTradeConfigEntity;
import cn.bitflash.vip.trade.feign.RedisKey;
import cn.bitflash.vip.trade.feign.TradeCommon;
import cn.bitflash.vip.trade.feign.TradeFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;

/**
 * 获取用户交易接口
 *
 * @author wangjun
 * @version 2018年7月4日上午9:44:17
 */
@RestController
@RequestMapping("/trade")
@Api(value = "用户交易", tags = "卖出，")
public class AddOrCancel {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TradeFeign tradeFeign;

    @Autowired
    private RedisUtils redisUtils;


    @Login
    @PostMapping("publish")
    @Transactional
    @ApiOperation(value = "卖出操作")
    public R publish(@RequestAttribute("uid") String uid, @ApiParam @RequestParam String quantity, @ApiParam @RequestParam String price) {

        UserAssetsNpcEntity userAssetsNpcEntity = tradeFeign.selectAccountByUid(uid);
        // 先校验出售数量是否大于已有数量
        BigDecimal total = new BigDecimal(userAssetsNpcEntity.getNpcAssets());
        logger.info("uid:" + userAssetsNpcEntity.getUid() + ",卖出数量:" + quantity + ",价格:" + price);
        if (StringUtils.isNotBlank(price) && StringUtils.isNotBlank(quantity)) {

            BigDecimal priceB = new BigDecimal(price);
            BigDecimal minPrice = new BigDecimal(TradeCommon.MIN_PRICE);
            if (priceB.compareTo(minPrice) <= -1) {
                return R.error("最低价格为0.33!");
            }
            if (Integer.valueOf(quantity) <= 0) {
                return R.error("发布数量最小为100!");
            }
            BigDecimal quantityB = new BigDecimal(quantity);

            // 卖出数量
            double quantityD = Double.parseDouble(quantity);
            //必须为100的整数倍
            if (quantityD % 100 == 0) {

                UserTradeConfigEntity userTradeConfigEntity = tradeFeign.selectTradeConfigById(1);
                if (null != userTradeConfigEntity) {

                    float poundage = userTradeConfigEntity.getPoundage();
                    logger.info("poundage:" + poundage);
                    // 手续费 = 卖出数量 * 0.05
                    double percent = quantityD * poundage;

                    BigDecimal percentB = new BigDecimal(Math.floor(percent));
                    // 总卖出数量 = 卖出数量 + 手续费
                    BigDecimal purchase = quantityB.add(percentB);
                    // 等于1表示total大于percentB,可以交易
                    if (total.compareTo(purchase) == 1 || total.compareTo(purchase) == 0) {
                        // 2.卖出数量
                        BigDecimal quantityBig = new BigDecimal(quantity);
                        // 卖出量-调节释放
                        BigDecimal subtract = quantityBig.subtract(new BigDecimal(userAssetsNpcEntity.getNpcAssets()));
                        if(subtract.doubleValue() >= 0){
                            BigDecimal availableAssets = new BigDecimal(userAssetsNpcEntity.getNpcAssets()).subtract(new BigDecimal(quantity));
                            userAssetsNpcEntity.setNpcAssets(availableAssets.subtract(percentB).floatValue());
                        }

//                        if (subtract.doubleValue() > 0 || subtract.doubleValue() == 0) {
//                            userAccount.setRegulateRelease(new BigDecimal(0.00));
//                            BigDecimal regulateIncome = userAccount.getRegulateIncome().subtract(subtract);
//                            userAccount.setRegulateIncome(regulateIncome.subtract(percentB));
//                            BigDecimal availableAssets = userAccount.getAvailableAssets().subtract(new BigDecimal(quantity));
//                            userAccount.setAvailableAssets(availableAssets.subtract(percentB));
//                        } else {
//                            BigDecimal release = quantityBig.add(percentB);
//                            BigDecimal availableAssets = userAccount.getAvailableAssets().subtract(new BigDecimal(quantity));
//                            userAccount.setAvailableAssets(availableAssets.subtract(percentB));
//                            BigDecimal RegulatRelease = userAccount.getRegulateRelease().subtract(release);
//                            userAccount.setRegulateRelease(RegulatRelease);
//                        }

                        tradeFeign.updateUserAccount(userAssetsNpcEntity);

                        // 添加卖出记录
                        UserMarketTradeEntity userTrade = new UserMarketTradeEntity();
                        String orderId = "00"+ RandomStringUtils.randomNumeric(6);
                        userTrade.setId(orderId);
                        userTrade.setPrice(Float.parseFloat(price));
                        userTrade.setState(TradeCommon.STATE_SELL);
                        userTrade.setQuantity(Float.parseFloat(quantity));
                        userTrade.setPurchaseUid(userAssetsNpcEntity.getUid());
                        userTrade.setCreateTime(new Date());
                        tradeFeign.insertOrUpdateTrade(userTrade);

                        // 1.先扣除手续费，可用于撤消
                        TradePoundageEntity tradePoundageEntity = new TradePoundageEntity();
                        tradePoundageEntity.setUserTradeId(orderId);
                        tradePoundageEntity.setUid(userAssetsNpcEntity.getUid());
                        tradePoundageEntity.setPoundage(percentB.floatValue());
                        tradePoundageEntity.setCreateTime(new Date());
                        tradeFeign.insertTradePoundage(tradePoundageEntity);
                        return R.ok();
                    } else {
                        return R.error().put("code", "1");
                    }
                }

            } else {
                return R.error("卖出数量必须为100的倍数！");
            }
        } else {
            return R.error("参数不能为空！");
        }
        return R.ok();
    }

    @Login
    @PostMapping("responseTrade")
    @ApiOperation(value = "卖出记录")
    public R responseTrade(@RequestAttribute("uid") String uid) {

        UserAssetsNpcEntity userAccount = tradeFeign.selectAccountByUid(uid);
        Map<String, Object> returnMap = null;
        if (null != userAccount) {
            returnMap = tradeFeign.responseTrade(userAccount.getUid());
            //可卖份数 = 可用额度 / 100

            if (userAccount.getNpcAssets()<=0) {
                returnMap.put("number", "0");
            } else {
                double number = Double.valueOf(userAccount.getNpcAssets()) / TradeCommon.MIN_NUMBER;
                returnMap.put("number", number);
            }
//            if (userAccount.getNpcAssets().compareTo(new BigDecimal(0)) <= -1) {
//                returnMap.put("number", "0");
//            } else {
//                // BigDecimal bigDecimal = userAccount.getAvailableAssets().divide(new BigDecimal(Common.MIN_PRICE));
//                double number = Double.valueOf(userAccount.getAvailableAssets().toString()) / Common.MIN_NUMBER;
//                returnMap.put("number", number);
//            }

            String availableAssets = TradeCommon.decimalFormat(Double.valueOf(userAccount.getNpcAssets()));
            returnMap.put("availableAssets", availableAssets);
        }
        return R.ok().put("userAccount", returnMap);
    }

    @Login
    @PostMapping("addLock")
    @ApiOperation("下单")
    public R addLock(@ApiParam(value = "订单id") @RequestParam String orderId, @RequestAttribute("uid") String uid) throws ParseException {
        UserMarketTradeEntity userTradeEntity = tradeFeign.selectTradeById(orderId);
        if (userTradeEntity.getState().equals(TradeCommon.STATE_CANCEL)) {
            return R.error(501, "订单已经被撤销,无法锁定");
        }
        //下单次数缓存key
        String countKey = RedisKey.COUNT_LOCK + uid;
        Integer count = redisUtils.get(countKey, Integer.class) == null ? 0 : redisUtils.get(countKey, Integer.class);
        //下单最大次数
        int trade = Integer.valueOf(tradeFeign.getVal(TradeCommon.LOCK_TRADE));
        if (count < trade) {
            String[] str = redisUtils.get(orderId, String[].class);
            if (str == null || str.length == 0) {
                //统计锁定数量
                str = new String[2];
                str[0] = orderId;
                str[1] = uid;
                redisUtils.set(orderId, str, 60 * 60);
                //当天时间凌晨23:59:59的秒数
                long tomorrow = LocalDateTime.now().withHour(23)
                        .withMinute(59)
                        .withSecond(59).toEpochSecond(ZoneOffset.of("+8"));
                //当前时间秒数
                long now = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
                //设置过期时间为当天剩余时间的秒数
                redisUtils.set(countKey, ++count, (tomorrow - now));
                //更新订单状态
                userTradeEntity.setState(TradeCommon.STATE_LOCK);
                userTradeEntity.setPurchaseUid(uid);
                tradeFeign.updateTrade(userTradeEntity);
                logger.info("当前锁定订单的数量为：" + count);
                logger.info("下单addLock:订单号" + orderId);
                return R.ok();
            } else if (str[1].equals(uid)) {
                return R.error(502, "订单被锁定,本人锁定");
            }
            return R.error(503, "订单已经被锁定");
        }
        return R.error(504, "当天锁定数量已到上限");


    }
}

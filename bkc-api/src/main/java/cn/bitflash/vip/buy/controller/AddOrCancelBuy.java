package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.BuyPoundageEntity;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketBuyHistoryEntity;
import cn.bitflash.utils.R;
import cn.bitflash.utils.RedisUtils;
import cn.bitflash.vip.buy.feign.BuyFeign;
import cn.bitflash.vip.trade.feign.RedisKey;
import cn.bitflash.vip.trade.feign.TradeCommon;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;

import static cn.bitflash.vip.buy.controller.BuyCommon.*;

@RestController
@RequestMapping("/buy")
public class AddOrCancelBuy {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuyFeign feign;

    @Autowired
    private TradeUtil tradeUtil;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * --------------发布---------------
     *
     * @param userMarketBuyEntity 订单信息
     * @return 交易状态
     */
    @Login
    @PostMapping("publish")
    public R addBuyMessage(@RequestBody UserMarketBuyEntity userMarketBuyEntity, @RequestAttribute("uid") String uid) {

        if (userMarketBuyEntity == null) {
            return R.error(501, "求购信息为空");
        }
        BigDecimal num = userMarketBuyEntity.getQuantity();
        if (num.floatValue() % 100 != 0 || num.compareTo(new BigDecimal(100)) == -1) {
            return R.error(502, "求购数量最低为100，且为100的倍数");
        }

        String orderId = "00" + RandomStringUtils.randomNumeric(6);
        userMarketBuyEntity.setId(orderId);
        userMarketBuyEntity.setPurchaseUid(uid);
        userMarketBuyEntity.setCreateTime(new Date());
        userMarketBuyEntity.setState(ORDER_STATE_PUBLISH);
        userMarketBuyEntity.setIsRead(IS_NOT_READED);
        feign.insertBuy(userMarketBuyEntity);
        return R.ok();
    }

    /**
     * --------------点击撤销--------------
     */
    @PostMapping("recall")
    @Transactional(propagation = Propagation.REQUIRED)
    public R cancelBuyMessage(@RequestParam String id) {
        UserMarketBuyEntity ub = feign.selectBuyById(id);
        if (ub == null || !ORDER_STATE_PUBLISH.equals(ub.getState())) {
            return R.error("订单不存在");
        }
        //user_buy删除记录
        feign.deleteBuyById(id);
        //user_buy_histoty添加该记录为撤销完成
        UserMarketBuyHistoryEntity userMarketBuyHistoryEntity = new UserMarketBuyHistoryEntity();
        userMarketBuyHistoryEntity.setUserBuyId(id);
        userMarketBuyHistoryEntity.setOrderState(ORDER_STATE_CANCEL);
        userMarketBuyHistoryEntity.setPrice(ub.getPrice());
        userMarketBuyHistoryEntity.setQuantity(ub.getQuantity());
        userMarketBuyHistoryEntity.setPurchaseUid(ub.getPurchaseUid());
        userMarketBuyHistoryEntity.setSellUid(null);
        userMarketBuyHistoryEntity.setFinishTime(new Date());
        feign.insertHistory(userMarketBuyHistoryEntity);

        return R.ok();
    }

    /**
     * ---------------下单----------------
     *
     * @param id 订单id
     * @return 交易状态
     * <p>
     * 1.查询手续费，并从卖出者账号中扣除。如资金不足抛出错误
     * 2.添加手续费到trade_poundage
     * 3.修改user_buy中state为‘1’交易中状态
     * 4.添加订单到user_buy_history
     * 5.修改求购者
     */
    @Login
    @PostMapping("click")
    @Transactional(propagation = Propagation.REQUIRED)
    public R addBuyMessageHistory(@RequestParam("id") String id, @RequestAttribute("uid") String uid) {

        //查询订单是否存在
        UserMarketBuyEntity userMarketBuyEntity = feign.selectBuyById(id);
        if(null != userMarketBuyEntity) {
            if (userMarketBuyEntity == null || !ORDER_STATE_PUBLISH.equals(userMarketBuyEntity.getState())) {
                return R.error("订单不存在");
            }

            if (userMarketBuyEntity.getState().equals(BuyCommon.ORDER_STATE_CANCEL)) {
                return R.error(501, "订单已经被撤销,无法锁定");
            }

            //获取手续费
            Map<String, BigDecimal> map = tradeUtil.poundage(id);
            //扣除手续费
            boolean dec = tradeUtil.deduct(map.get("totalQuantity"), uid);
            if (!dec) {
                return R.error("余额不足");
            }

            //下单次数缓存key
            String countKey = RedisKey.COUNT_LOCK + uid;
            Integer count = redisUtils.get(countKey, Integer.class) == null ? 0 : redisUtils.get(countKey, Integer.class);
            //下单最大次数
            String key = TradeCommon.LOCK_TRADE;
            String param =  feign.getVal(key);
            int trade = Integer.valueOf(param);

            if (count < trade) {
                String[] str = redisUtils.get(id, String[].class);
                if (str == null || str.length == 0) {
                    //统计锁定数量
                    str = new String[2];
                    str[0] = id;
                    str[1] = uid;
                    //redisUtils.set(orderId, str, 60);
                    redisUtils.set(id, str, 60 * 60);
                    //当天时间凌晨23:59:59的秒数
                    long tomorrow = LocalDateTime.now().withHour(23)
                            .withMinute(59)
                            .withSecond(59).toEpochSecond(ZoneOffset.of("+8"));
                    //当前时间秒数
                    long now = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
                    //设置过期时间为当天剩余时间的秒数
                    redisUtils.set(countKey, ++count, (tomorrow - now));

                    //添加手续费记录
                    BuyPoundageEntity buyPoundageEntity = new BuyPoundageEntity();
                    buyPoundageEntity.setUserBuyId(id);
                    buyPoundageEntity.setSellUid(uid);
                    buyPoundageEntity.setPoundage(map.get("totalPoundage"));
                    buyPoundageEntity.setQuantity(map.get("buyQuantity"));
                    buyPoundageEntity.setCreateTime(new Date());
                    feign.insertPoundage(buyPoundageEntity);

                    //修改user_buy
                    userMarketBuyEntity.setSellUid(uid);
                    userMarketBuyEntity.setIsRead(IS_NOT_READED);
                    userMarketBuyEntity.setState(ORDER_STATE_STEP1);
                    feign.updateBuyById(userMarketBuyEntity);

                    logger.info("当前锁定订单的数量为：" + count);
                    logger.info("下单addLock:订单号" + id);
                    return R.ok();
                } else if (str[1].equals(uid)) {
                    return R.error(502, "订单被锁定,本人锁定");
                }
                return R.error(503, "订单已经被锁定");
            } else {
                return R.error(504, "当天锁定数量已到上限");
            }
        }
        return  R.error(505, "当前订单不存在");
    }

}

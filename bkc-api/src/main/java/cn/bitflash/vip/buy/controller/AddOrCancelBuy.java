package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.BuyPoundageEntity;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketBuyHistoryEntity;
import cn.bitflash.entity.UserMarketConfigEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cn.bitflash.vip.buy.controller.BuyCommon.*;

@RestController
@RequestMapping("/buy")
public class AddOrCancelBuy {

    @Autowired
    private BuyFeign feign;

    @Autowired
    private TradeUtil tradeUtil;

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
        Float num = userMarketBuyEntity.getQuantity();
        if (num % 100 != 0 || num < 100) {
            return R.error(502, "求购数量最低为100，且为100的倍数");
        }

        String orderId = "00" + RandomStringUtils.randomNumeric(6);
        userMarketBuyEntity.setId(orderId);
        userMarketBuyEntity.setPurchaseUid(uid);
        userMarketBuyEntity.setCreateTime(new Date());
        userMarketBuyEntity.setState(ORDER_STATE_PUBLISH);
        feign.insertBuy(userMarketBuyEntity);

        return R.ok().put("code", SUCCESS);
    }

    /**
     * --------------点击撤销--------------
     */
    @PostMapping("recall")
    @Transactional(propagation = Propagation.REQUIRED)
    public R cancelBuyMessage(@RequestParam String id) {
        UserMarketBuyEntity ub = feign.selectBuyById(id);
        if (ub == null || !ORDER_STATE_PUBLISH.equals(ub.getState())) {
            return R.ok().put("code", FAIL);
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

        return R.ok().put("code", SUCCESS);
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
        if (userMarketBuyEntity == null || !ORDER_STATE_PUBLISH.equals(userMarketBuyEntity.getState())) {
            return R.ok().put("code", TRADEMISS);
        }
        //获取手续费
        Map<String, Float> map = tradeUtil.poundage(id);
        //扣除手续费
        boolean dec = tradeUtil.deduct(map.get("totalQuantity"), uid);
        if (!dec) {
            return R.ok().put("code", FAIL);
        }

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
        userMarketBuyEntity.setState(ORDER_STATE_STEP1);
        feign.updateBuyById(userMarketBuyEntity);

        return R.ok().put("code", SUCCESS);
    }

}

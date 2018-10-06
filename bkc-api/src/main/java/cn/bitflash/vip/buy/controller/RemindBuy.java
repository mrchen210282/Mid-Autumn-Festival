package cn.bitflash.vip.buy.controller;

import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.utils.GeTuiSendMessage;
import cn.bitflash.utils.R;
import cn.bitflash.utils.RedisUtils;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.bitflash.vip.buy.controller.BuyCommon.*;


@RestController
@RequestMapping("/buy")
public class RemindBuy {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private BuyFeign feign;


    /**
     * -------------点击催单(待收款/待收币)------------
     */
    @PostMapping("remind")
    @Transactional(propagation = Propagation.REQUIRED)
    public R reminders(@RequestParam("id") String id) {
        UserMarketBuyEntity userMarketBuyEntity = feign.selectBuyById(id);
        //获取Cid
        String cid = null;
        //获取推送信息
        String text = null;
        if (ORDER_STATE_STEP1.equals(userMarketBuyEntity.getState())) {
            cid = feign.selectCid(userMarketBuyEntity.getPurchaseUid());
            text = feign.getVal("paymoney");
        }
        if (ORDER_STATE_STEP2.equals(userMarketBuyEntity.getState())) {
            cid = feign.selectCid(userMarketBuyEntity.getSellUid());
            text = feign.getVal("reminders");
        }
        String idVal = redisUtils.get(BuyCommon.ADD_LOCKNUM + id);
        if (StringUtils.isBlank(idVal)) {
            try {
                GeTuiSendMessage.sendSingleMessage(text, cid);
                redisUtils.set(BuyCommon.ADD_LOCKNUM + id, id, 60 * 60);
            } catch (Exception e) {
                return R.error("推送失败");
            }
        }
        return R.ok().put("code", SUCCESS);
    }

    /**
     * -------------判定是否催单(待收币)------------
     */
    @PostMapping("remind/decide")
    public R checkReminders(@RequestParam("id") String id) {
        if (redisUtils.get(BuyCommon.ADD_LOCKNUM + id) == null || "".equals(redisUtils.get(BuyCommon.ADD_LOCKNUM + id))) {
            return R.ok().put("state", "0");
        } else {
            return R.ok().put("state", "1");
        }
    }
}

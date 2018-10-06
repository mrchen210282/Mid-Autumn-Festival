package cn.bitflash.vip.buy.controller;

import cn.bitflash.entities.UserBuyEntity;
import cn.bitflash.util.Common;
import cn.bitflash.util.GeTuiSendMessage;
import cn.bitflash.util.R;
import cn.bitflash.util.RedisUtils;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.bitflash.util.Common.*;

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
        UserBuyEntity userBuyEntity = feign.selectBuyById(id);
        //获取Cid
        String cid = null;
        //获取推送信息
        String text = null;
        if (ORDER_STATE_STEP1.equals(userBuyEntity.getState())) {
            cid = feign.selectCid(userBuyEntity.getPurchaseUid());
            text = feign.getVal("paymoney");
        }
        if (ORDER_STATE_STEP2.equals(userBuyEntity.getState())) {
            cid = feign.selectCid(userBuyEntity.getSellUid());
            text = feign.getVal("reminders");
        }
        String idVal = redisUtils.get(Common.ADD_LOCKNUM + id);
        if (StringUtils.isBlank(idVal)) {
            try {
                GeTuiSendMessage.sendSingleMessage(text, cid);
                redisUtils.set(Common.ADD_LOCKNUM + id, id, 60 * 60);
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
        if (redisUtils.get(Common.ADD_LOCKNUM + id) == null || "".equals(redisUtils.get(Common.ADD_LOCKNUM + id))) {
            return R.ok().put("state", "0");
        } else {
            return R.ok().put("state", "1");
        }
    }
}

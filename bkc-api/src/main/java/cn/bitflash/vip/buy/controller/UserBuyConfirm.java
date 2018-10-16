package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.utils.R;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import static cn.bitflash.vip.buy.controller.BuyCommon.*;

@RestController
@RequestMapping("/buy")
public class UserBuyConfirm {

    @Autowired
    private TradeUtil tradeUtil;

    @Autowired
    private BuyFeign feign;

    /**
     * --------------点击确认(待确认)-----------
     */
    @Login
    @PostMapping("orderConfirm")
    public R payCoin(@RequestParam("id") String id, @RequestParam("pwd") String pwd, @RequestAttribute("uid") String uid) {

        //判断交易密码是否正确
        UserSecretEntity userSecretEntity = feign.selectUid(uid);
        //交易密码不正确
        if (!pwd.equals(userSecretEntity.getPayPassword())) {
            return R.error("交易密码不正确");
        }

        //判断对方是否点击申诉
        UserComplaintEntity userComplaintEntity = feign.selectComplaintById(id);
        if (userComplaintEntity != null) {
            feign.deleteComplaint(id);
        }

        //手续费
        Map<String, BigDecimal> map = tradeUtil.poundage(id);
        BigDecimal buyQuantity = map.get("buyQuantity");
        BigDecimal totalQuantity = map.get("totalQuantity");

        //充值+扣款
        UserMarketBuyEntity userMarketBuyEntity = feign.selectBuyById(id);
        UserAssetsNpcEntity purchaseAssets = feign.selectAccountById(userMarketBuyEntity.getPurchaseUid());
        UserAssetsNpcEntity sellAssets = feign.selectAccountById(userMarketBuyEntity.getSellUid());
        purchaseAssets.setAvailableAssets(purchaseAssets.getAvailableAssets().add(buyQuantity));
        sellAssets.setAvailableAssets(sellAssets.getAvailableAssets().subtract(totalQuantity));
        feign.updateAccountById(purchaseAssets);
        feign.updateAccountById(sellAssets);

        //添加手续费到user_brokerage中
        BigDecimal totalPoundage = map.get("totalPoundage");
        UserBrokerageEntity userBrokerageEntity = feign.selectBrokerageById(1);
        userBrokerageEntity.setSellBrokerage(userBrokerageEntity.getSellBrokerage().add(totalPoundage));
        feign.updateBrokerageById(userBrokerageEntity);

        //删除Buy_POUNDAGE
        feign.deletePoundage(id);

        //添加到user_buy_history
        UserMarketBuyHistoryEntity userMarketBuyHistoryEntity = new UserMarketBuyHistoryEntity();
        userMarketBuyHistoryEntity.setFinishTime(new Date());
        userMarketBuyHistoryEntity.setOrderState(ORDER_STATE_FINISH);
        userMarketBuyHistoryEntity.setPurchaseUid(userMarketBuyEntity.getPurchaseUid());
        userMarketBuyHistoryEntity.setSellUid(uid);
        userMarketBuyHistoryEntity.setUserBuyId(id);
        userMarketBuyHistoryEntity.setIsRead(IS_NOT_READED);
        userMarketBuyHistoryEntity.setQuantity(userMarketBuyEntity.getQuantity());
        userMarketBuyHistoryEntity.setPrice(userMarketBuyEntity.getPrice());
        feign.insertHistory(userMarketBuyHistoryEntity);

        //删除user_buy记录
        feign.deleteBuyById(id);

        return R.ok().put("code", SUCCESS);
    }
}

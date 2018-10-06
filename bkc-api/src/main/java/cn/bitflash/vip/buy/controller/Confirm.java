package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketBuyHistoryEntity;
import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import static cn.bitflash.vip.buy.controller.BuyCommon.*;

@RestController
@RequestMapping("/buy")
public class Confirm {

    private TradeUtil tradeUtil;

    private BuyFeign feign;

    /**
     * --------------点击确认(待确认)-----------
     */
    @Login
    @PostMapping("")
    @Transactional(propagation = Propagation.REQUIRED)
    public R payCoin(@RequestParam("id") String id, @RequestParam("pwd") String pwd, @RequestAttribute("uid") String uid) {

        //判断交易密码是否正确
        UserSecretEntity userSecretEntity = feign.selectUid(uid);
        //交易密码不正确
        if (!pwd.equals(userSecretEntity.getPayPassword())) {
            return R.ok().put("code", "3");
        }
        //手续费
        Map<String, Float> map = tradeUtil.poundage(id);
        float buyQuantity = map.get("buyQuantity");

        //充值
        UserMarketBuyEntity userMarketBuyEntity = feign.selectBuyById(id);
        UserAssetsNpcEntity userAssetsNpcEntity = feign.selectAccountById(userMarketBuyEntity.getPurchaseUid());
        userAssetsNpcEntity.setNpcAssets(userAssetsNpcEntity.getNpcAssets()+buyQuantity);
        feign.updateAccountById(userAssetsNpcEntity);

        //添加手续费到user_brokerage中
//        BigDecimal totalPoundage = new BigDecimal(map.get("totalPoundage"));
//        UserBrokerageEntity userBrokerageEntity = feign.selectBrokerageById(1);
//        userBrokerageEntity.setSellBrokerage(userBrokerageEntity.getSellBrokerage().add(totalPoundage));
//        feign.updateBrokerageById(userBrokerageEntity);

        //删除Buy_POUNDAGE
        feign.deletePoundage(id);

        //添加到user_buy_history
        UserMarketBuyHistoryEntity userMarketBuyHistoryEntity = new UserMarketBuyHistoryEntity();
        userMarketBuyHistoryEntity.setFinishTime(new Date());
        userMarketBuyHistoryEntity.setOrderState(ORDER_STATE_FINISH);
        userMarketBuyHistoryEntity.setPurchaseUid(userMarketBuyEntity.getPurchaseUid());
        userMarketBuyHistoryEntity.setSellUid(uid);
        userMarketBuyHistoryEntity.setUserBuyId(id);
        userMarketBuyHistoryEntity.setQuantity(userMarketBuyEntity.getQuantity());
        userMarketBuyHistoryEntity.setPrice(userMarketBuyEntity.getPrice());
        feign.insertHistory(userMarketBuyHistoryEntity);

        //删除user_buy记录
        feign.deleteBuyById(id);

        return R.ok().put("code", SUCCESS);
    }
}

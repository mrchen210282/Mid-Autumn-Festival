package cn.bitflash.vip.buy.controller;

import cn.bitflash.entity.BuyPoundageEntity;
import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static cn.bitflash.vip.buy.controller.BuyCommon.*;


@RestController
@RequestMapping("/buy/pendingPay")
public class PendingPay {

    @Autowired
    private BuyFeign buyFeign;

    /**
     * --------点击已付款(待付款)-----------
     */
    @PostMapping("pay")
    @Transactional(propagation = Propagation.REQUIRED)
    public R payMoney(@RequestParam("id") String id) {
        UserMarketBuyEntity userMarketBuyEntity = buyFeign.selectBuyById(id);
        //设置支付时间,user_buy订单状态
        userMarketBuyEntity.setPayTime(new Date());
        userMarketBuyEntity.setState(ORDER_STATE_STEP2);
        buyFeign.updateBuyById(userMarketBuyEntity);
        //判断对方是否点击申诉
        UserComplaintEntity userComplaintEntity = buyFeign.selectComplaintById(id);
        if (userComplaintEntity != null) {
            buyFeign.deleteComplaint(id);
        }
        return R.ok().put("code", SUCCESS);
    }

    /**
     * -------------点击取消(待付款)------------
     */
    @PostMapping("cancel")
    @Transactional(propagation = Propagation.REQUIRED)
    public R recall(@RequestParam("id") String id) {
        //查询uid
        UserMarketBuyEntity userMarketBuyEntity = buyFeign.selectBuyById(id);
        //获取trade_poundage手续费，并返还，删除该信息
        BuyPoundageEntity buyPoundageEntity = buyFeign.selectPoundageById(id);
        UserAssetsNpcEntity userAssetsNpcEntity = buyFeign.selectAccountById(userMarketBuyEntity.getSellUid());
        userAssetsNpcEntity.setAvailableAssets(buyPoundageEntity.getPoundage() + buyPoundageEntity.getQuantity() + userAssetsNpcEntity.getAvailableAssets());
        buyFeign.updateAccountById(userAssetsNpcEntity);
        buyFeign.deletePoundage(id);
        //判断对方是否点击申诉
        UserComplaintEntity userComplaintEntity = buyFeign.selectComplaintById(id);
        if (userComplaintEntity != null) {
            buyFeign.deleteComplaint(id);
        }
        //删除求购历史订单
        buyFeign.deleteBuyById(id);
        return R.ok().put("code", SUCCESS);
    }
}
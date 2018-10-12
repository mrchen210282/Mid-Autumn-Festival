package cn.bitflash.vip.buy.controller;

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
@RequestMapping("/buy")
public class BuyAppeal {

    @Autowired
    private BuyFeign feign;

    /**
     * --------------点击申诉(待收币)---------
     */
    @PostMapping("appeal")
    @Transactional(propagation = Propagation.REQUIRED)
    public R appeal(@RequestParam("id") String id) {
        //修改订单状态
        UserMarketBuyEntity userMarketBuyEntity = feign.selectBuyById(id);
        userMarketBuyEntity.setState(ORDER_STATE_APPEAL);
        feign.updateBuyById(userMarketBuyEntity);
        //添加订单到申诉表中
        UserComplaintEntity userComplaintEntity = new UserComplaintEntity();
        userComplaintEntity.setComplaintState("1");
        userComplaintEntity.setComplaintUid(userMarketBuyEntity.getPurchaseUid());
        userComplaintEntity.setContactsUid(userMarketBuyEntity.getSellUid());
        userComplaintEntity.setCreateTime(new Date());
        userComplaintEntity.setOrderId(id);
        userComplaintEntity.setIsRead(IS_NOT_READED);
        userComplaintEntity.setOrderState(userMarketBuyEntity.getState());
        feign.insertComplaint(userComplaintEntity);
        return R.ok().put("code", SUCCESS);
    }
}

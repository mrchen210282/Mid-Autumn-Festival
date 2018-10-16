package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketBuyHistoryEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.buy.Bean.UserBuyBean;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.bitflash.vip.buy.controller.BuyCommon.*;


@RestController
@RequestMapping("/buy/show")
public class Show {

    @Autowired
    private BuyFeign feign;

    /**-----------------------------------------------显示求购信息列表-----------------------------------------------------*/

    /**
     * ----------------交易页-----------------
     *
     * @param pages 分页
     * @return 除用户所有求购信息
     */
    @Login
    @PostMapping("buying")
    public R showNeedMessage(@RequestAttribute("uid") String uid, @RequestParam("pages") String pages) {
        List<UserBuyBean> ub = feign.showBuying(uid, Integer.valueOf(pages));
        if (ub == null || ub.size() < 0) {
            return R.error("暂时没有求购信息");
        }
        Integer count = feign.showBuyingCount(uid);
        return R.ok().put("count", count).put("list", ub);
    }

    /**
     * ---------------订单页----------------
     *
     * @param pages 分页
     * @return 用户的所有交易信息
     */
    @Login
    @PostMapping("order")
    public R showUserBuyMessage(@RequestAttribute("uid") String uid, @RequestParam("pages") String pages) {

        List<UserBuyBean> userBuyBeans = feign.showOrder(uid, Integer.valueOf(pages));

        for (UserBuyBean userBuyBean : userBuyBeans) {
            //卖家
            if (uid.equals(userBuyBean.getSellUid())) {
                if (ORDER_STATE_STEP1.equals(userBuyBean.getState())) {
                    userBuyBean.setState("待收款");
                } else if (ORDER_STATE_STEP2.equals(userBuyBean.getState())) {
                    userBuyBean.setState("待确认");
                }
            }
            //买家
            if (uid.equals(userBuyBean.getPurchaseUid())) {
                if (ORDER_STATE_PUBLISH.equals(userBuyBean.getState())) {
                    userBuyBean.setState("可撤销");
                } else if (ORDER_STATE_STEP1.equals(userBuyBean.getState())) {
                    userBuyBean.setState("待付款");
                } else if (ORDER_STATE_STEP2.equals(userBuyBean.getState())) {
                    userBuyBean.setState("待收币");
                }
            }
        }

        Integer count = feign.showOrderCount(uid);
        return R.ok().put("userBuyBeans", userBuyBeans).put("count", count);
    }

    @Login
    @PostMapping("state")
    public R showUserState(@RequestAttribute("uid") String uid, @RequestParam("orderId") String orderId) {
        UserMarketBuyEntity userMarketBuyEntity = feign.selectBuyById(orderId);
        String state = "";
        if(userMarketBuyEntity == null){
            UserMarketBuyHistoryEntity userMarketBuyHistoryEntity = feign.selectHistoryById(orderId);
            if(userMarketBuyHistoryEntity != null && userMarketBuyHistoryEntity.getOrderState().equals(ORDER_STATE_FINISH)){
                return R.ok().put("state","已完成");
            }
            return R.error("订单不存在");
        }
        //卖家
        if (uid.equals(userMarketBuyEntity.getSellUid())) {
            if (ORDER_STATE_STEP1.equals(userMarketBuyEntity.getState())) {
                state="待收款";
            } else if (ORDER_STATE_STEP2.equals(userMarketBuyEntity.getState())) {
                state="待确认";
            }
        }
        //买家
        if (uid.equals(userMarketBuyEntity.getPurchaseUid())) {
            if (ORDER_STATE_PUBLISH.equals(userMarketBuyEntity.getState())) {
                state="可撤销";
            } else if (ORDER_STATE_STEP1.equals(userMarketBuyEntity.getState())) {
                state="待付款";
            } else if (ORDER_STATE_STEP2.equals(userMarketBuyEntity.getState())) {
                state="待收币";
            }
        }

        if (userMarketBuyEntity.getState().equals(ORDER_STATE_APPEAL)) {
            return R.ok().put("state","已申诉");
        }
        return R.ok().put("state", state);
    }
}

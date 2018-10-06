package cn.bitflash.vip.buy.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserBuyBean;
import cn.bitflash.util.R;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.bitflash.util.Common.*;

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
                if(ORDER_STATE_STEP1.equals(userBuyBean.getState())){
                    userBuyBean.setState("待收款");
                } else if(ORDER_STATE_STEP2.equals(userBuyBean.getState())){
                    userBuyBean.setState("待确认");
                }
            }
            //买家
            if (uid.equals(userBuyBean.getPurchaseUid())) {
                if(ORDER_STATE_PUBLISH.equals(userBuyBean.getState())){
                    userBuyBean.setState("可撤销");
                } else if(ORDER_STATE_STEP1.equals(userBuyBean.getState())){
                    userBuyBean.setState("待付款");
                } else if(ORDER_STATE_STEP2.equals(userBuyBean.getState())){
                    userBuyBean.setState("待收币");
                }
            }
        }

        Integer count = feign.showOrderCount(uid);
        return R.ok().put("userBuyBeans", userBuyBeans).put("count", count);
    }
}

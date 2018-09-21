package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.entity.UserTradeHistoryEntity;
import cn.bitflash.util.DateUtils;
import cn.bitflash.util.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "账户数量Con", tags = {"首页显示数据"})
public class Account {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("/accountInfo")
    public R accountInfo(@RequestAttribute("uid") String uid) {

        String vip = userFeign.selectUserInfoByColumn(uid,"vip");
        UserAccountEntity account = userFeign.selectAccountByUid(uid);
        //返回值集合
        Map<String, Object> map = new HashMap<>();
        //vip等级
        map.put("vip",vip);
        //可用资产
        map.put("avaliableAssets", account.getAvailableAssets());
        if (!vip.equals("0")) {
            /**
             * yesterDayIncome 昨日收入
             * totelAssets   总收入
             * frozenAssets 冻结资产
             */
            map.put("yesterDayIncome", account.getDailyIncome());
            map.put("totelAssets", account.getTotelIncome());
            map.put("frozenAssets", account.getFrozenAssets());

        } else {
            Date yesterday = DateUtils.addDateDays(new Date(), -1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String yester = sdf.format(yesterday);
            //交易
            List<UserTradeHistoryEntity> trades = userFeign.selectTradeHistoryList(uid);
            Double alltrade = 0d;
            Double yesttrade = 0d;
            if (trades != null && trades.size() > 0) {
                alltrade = trades.stream().mapToDouble(u -> {
                    Double money = u.getPurchaseQuantity().doubleValue();
                    return money;
                }).sum();
                //昨日交易购买
                yesttrade = trades.stream().filter(u -> u.getFinishTime() != null && sdf.format(u.getFinishTime()).equals(yester)).mapToDouble(u -> {
                    Double money = u.getSellQuantity().doubleValue();
                    return money;
                }).sum();
            }
            //求购
            List<UserBuyHistoryEntity> buys = userFeign.selectBuyHistoryList(uid);
            Double allbuy = 0d;
            Double yestbuy = 0d;
            if (buys != null && buys.size() > 0) {
                allbuy = buys.stream().mapToDouble(u -> {
                    Double money = u.getQuantity().doubleValue();
                    return money;
                }).sum();
                //昨日求购购买
                yestbuy = buys.stream().filter(u -> u.getFinishTime() != null && sdf.format(u.getFinishTime()).equals(yester)).mapToDouble(u -> {
                    Double money = u.getQuantity().doubleValue();
                    return money;
                }).sum();
            }

            /**
             * allbuy 总购买
             * yesterdaybuy 昨日购买
             */
            map.put("allbuy", alltrade + allbuy);
            map.put("yesterdaybuy", yesttrade + yestbuy);
        }
        return R.ok(map);
    }
}

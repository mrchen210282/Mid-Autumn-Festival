package cn.bitflash.vip.buy.controller;

import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketConfigEntity;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Component
public class TradeUtil {

    @Autowired
    private BuyFeign feign;

    /**
     * ----------------------------手续费+订单数量------------------------
     */
    public Map<String, Float> poundage(String id) {
        UserMarketBuyEntity userMarketBuyEntity = feign.selectBuyById(id);
        if (userMarketBuyEntity == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("#########.##");
        //交易数量
        Float buyQuantity = Float.parseFloat(df.format(userMarketBuyEntity.getQuantity()));
        //手续费比率
        UserMarketConfigEntity userMarketConfigEntity = feign.selectPoundage(2);
        float poundage = userMarketConfigEntity.getPoundage();
        //手续费数量
        Float totalPoundage = buyQuantity * poundage;
        //实际交易总数量
        Float totalQuantity = buyQuantity + totalPoundage;
        //单价
        Float price = userMarketBuyEntity.getPrice();
        //总价格
        Float totalMoney = buyQuantity * (price);

        Map<String, Float> map = new HashMap<String, Float>();
        map.put("buyQuantity", buyQuantity);
        map.put("poundage", poundage);
        map.put("totalPoundage", totalPoundage);
        map.put("totalQuantity", totalQuantity);
        map.put("price", price);
        map.put("totalMoney", totalMoney);
        return map;
    }

    /**
     * --------------------------------扣款-------------------------------
     */
    public boolean deduct(float money, String uid) {
        UserAssetsNpcEntity userAssetsNpcEntity = feign.selectAccountById(uid);
        float deduction = userAssetsNpcEntity.getAvailableAssets() - money;
        if (deduction < 0) {
            return false;
        }
        return true;
    }

    /**
     * 添加提醒红点
     */
    public void remindMessageAdd(){

    }

    /**
     * 删除提醒红点
     */
    public void remindMessageDel(){

    }
}

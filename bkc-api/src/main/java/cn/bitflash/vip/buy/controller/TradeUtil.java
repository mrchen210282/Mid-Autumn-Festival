package cn.bitflash.vip.buy.controller;

import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.entity.UserMarketConfigEntity;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    public Map<String, BigDecimal> poundage(String id) {
        UserMarketBuyEntity userMarketBuyEntity = feign.selectBuyById(id);
        if (userMarketBuyEntity == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("#########.##");
        //交易数量
        BigDecimal buyQuantity = userMarketBuyEntity.getQuantity();
        //手续费比率
        UserMarketConfigEntity userMarketConfigEntity = feign.selectPoundage(2);
        BigDecimal poundage = userMarketConfigEntity.getPoundage();
        //手续费数量
        BigDecimal totalPoundage = buyQuantity.multiply(poundage);
        //实际交易总数量
        BigDecimal totalQuantity = buyQuantity.add(totalPoundage);
        //单价
        BigDecimal price = userMarketBuyEntity.getPrice();
        //总价格
        BigDecimal totalMoney = buyQuantity.multiply(price);

        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
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
    public boolean deduct(BigDecimal money, String uid) {
        UserAssetsNpcEntity userAssetsNpcEntity = feign.selectAccountById(uid);
        BigDecimal deduction = userAssetsNpcEntity.getAvailableAssets().subtract(money);
        if (deduction.compareTo(new BigDecimal(0)) == -1) {
            return false;
        }
        return true;
    }

}

package cn.bitflash.vip.buy.controller;

import cn.bitflash.entities.UserBuyEntity;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.vip.buy.feign.BuyFeign;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class TradeUtil {

    @Autowired
    private BuyFeign feign;

    /**
     * ----------------------------手续费+订单数量------------------------
     */
    public Map<String, Float> poundage(String id) {
        UserBuyEntity userBuy = feign.selectBuyById(id);
        if (userBuy == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("#########.##");
        //交易数量
        Float buyQuantity = Float.parseFloat(df.format(userBuy.getQuantity()));
        //手续费比率
        Float poundage = feign.selectPoundage(2);
        //手续费数量
        Float totalPoundage = buyQuantity * poundage;
        //实际交易总数量
        Float totalQuantity = buyQuantity + totalPoundage;
        //单价
        Float price = userBuy.getPrice();
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
    public boolean deduct(BigDecimal money, String uid) {
        UserAccountEntity userAccountEntity = feign.selectAccountById(uid);
        if (userAccountEntity.getAvailableAssets().compareTo(money) != -1) {
            if (userAccountEntity.getRegulateRelease().compareTo(money) != -1) {
                userAccountEntity.setRegulateRelease(userAccountEntity.getRegulateRelease().subtract(money));
                userAccountEntity.setAvailableAssets(userAccountEntity.getAvailableAssets().subtract(money));
            } else {
                userAccountEntity.setRegulateIncome(userAccountEntity.getRegulateIncome().subtract(money.subtract(userAccountEntity.getRegulateRelease())));
                userAccountEntity.setRegulateRelease(new BigDecimal(0));
                userAccountEntity.setAvailableAssets(userAccountEntity.getAvailableAssets().subtract(money));
            }
            feign.updateAccountById(userAccountEntity);
            return true;
        }
        return false;
    }
}

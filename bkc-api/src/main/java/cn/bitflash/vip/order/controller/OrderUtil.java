package cn.bitflash.vip.order.controller;

import cn.bitflash.entity.*;
import cn.bitflash.vip.buy.feign.BuyFeign;
import cn.bitflash.vip.order.feign.OrderFeign;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderUtil {

    @Autowired
    private OrderFeign orderFeign;

    /**
     * 手续费
     *
     * @param id
     * @param state
     * @return
     */
    public Map<String, Float> poundage(String id, String state, String orderState) {

        DecimalFormat df = new DecimalFormat("#########.##");
        Float buyQuantity = 0f;
        Float price = 0f;
        if ("SUCCESS".equals(orderState)) {
            if (state.equals("1")) {
                UserMarketBuyHistoryEntity userBuy = orderFeign.selectBuyHistoryById(id);
                price = Float.parseFloat(df.format(userBuy.getPrice()));
                buyQuantity = Float.parseFloat(df.format(userBuy.getQuantity()));
            } else if (state.equals("2")) {
                UserMarketTradeHistoryEntity userTradeEntity = orderFeign.selectTradeHistoryById(id);
                price = Float.parseFloat(df.format(userTradeEntity.getPrice()));
                buyQuantity = Float.parseFloat(df.format(userTradeEntity.getQuantity()));
            }
        }
        if ("REMIND".equals(orderState)) {
            if (state.equals("1")) {
                UserMarketBuyEntity userBuy = orderFeign.selectUserBuyById(id);
                price = Float.parseFloat(df.format(userBuy.getPrice()));
                buyQuantity = Float.parseFloat(df.format(userBuy.getQuantity()));
            } else if (state.equals("2")) {
                UserMarketTradeEntity userTradeEntity = orderFeign.selectTradeById(id);
                price = Float.parseFloat(df.format(userTradeEntity.getPrice()));
                buyQuantity = Float.parseFloat(df.format(userTradeEntity.getQuantity()));
            }
        }

        //手续费比率
        Float poundage = orderFeign.selectTradeConfigById(1).getPoundage();
        //手续费数量
        Float totalPoundage = buyQuantity * poundage;
        //实际交易总数量
        Float totalQuantity = buyQuantity + totalPoundage;
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
}

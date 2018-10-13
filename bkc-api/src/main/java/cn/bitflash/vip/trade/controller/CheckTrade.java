package cn.bitflash.vip.trade.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.TradePoundageEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.trade.entity.AllUserTradeBean;
import cn.bitflash.vip.trade.entity.UserTradeDetail;
import cn.bitflash.vip.trade.feign.TradeFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/trade")
@Api(value = "查询列表数据")
public class CheckTrade {

    @Autowired
    private TradeFeign tradeFeign;

    @Login
    @PostMapping("selectDetail")
    @ApiOperation(value = "查看买入订单明细")
    public R selectDetail(@ApiParam(value = "订单id") @RequestParam String id) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("state",null);
        if (StringUtils.isNotBlank(id)) {
            UserTradeDetail userTradeDetail = tradeFeign.selectDetail(map);
            TradePoundageEntity tradePoundageEntity = tradeFeign.selectTradePoundageById(id);
            if (null != tradePoundageEntity) {
                //扣除交易额=交易额+手续费
                BigDecimal deductAmount = userTradeDetail.getQuantity().add(tradePoundageEntity.getPoundage());
                userTradeDetail.setDeductAmount(deductAmount);
            }
            if (null != userTradeDetail) {
                //数量
                BigDecimal quantity = userTradeDetail.getQuantity();
                //价格
                BigDecimal price = userTradeDetail.getPrice();
                BigDecimal tradeAmount = price.multiply(quantity);
                userTradeDetail.setTradeAmount(tradeAmount);
            }
            return R.ok().put("userTradeBean", userTradeDetail);
        } else {
            return R.error("参数不能为空！");
        }
    }

    @Login
    @PostMapping("viewDetail")
    @ApiOperation(value = "查看买入订单明细")
    public R viewDetail(@ApiParam(value = "订单id") @RequestParam String id) {
        if (StringUtils.isNotBlank(id)) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",id);
            AllUserTradeBean alltrade = tradeFeign.queryDetail(map);
            TradePoundageEntity tradePoundageEntity = tradeFeign.selectTradePoundageById(id);
            if (null != tradePoundageEntity) {
                //扣除交易额=交易额+手续费
                BigDecimal deductAmount = alltrade.getQuantity().add(tradePoundageEntity.getPoundage());
                alltrade.setDeductAmount(deductAmount);
            }

            if (null != alltrade) {
                //数量
                BigDecimal quantity = alltrade.getQuantity();
                //价格
                BigDecimal price = alltrade.getPrice();
                BigDecimal tradeAmount = price.multiply(quantity);

                alltrade.setTradeAmount(tradeAmount);
            }
            return R.ok().put("userTradeBean", alltrade);
        } else {
            return R.error("参数不能为空！");
        }
    }

}

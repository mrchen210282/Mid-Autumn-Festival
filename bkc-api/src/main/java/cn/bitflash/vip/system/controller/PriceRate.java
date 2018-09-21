package cn.bitflash.vip.system.controller;


import cn.bitflash.annotation.Login;
import cn.bitflash.entity.PriceLinechartEntity;
import cn.bitflash.util.DateUtils;
import cn.bitflash.util.R;
import cn.bitflash.vip.system.entity.PriceChart;
import cn.bitflash.vip.system.feign.SystemFeign;
import io.swagger.annotations.Api;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system")
@Api(value = "价格折线图Con",tags={"显示折线图"})
public class PriceRate {

    @Autowired
    private SystemFeign systemFeign;

    /**
     * 获取当天起，前7天的价格
     *
     * @return
     */
    @Login
    @PostMapping("getWeekPriceRate")
    public R getWeekPriceRate() {
        Date now = new DateTime().withTimeAtStartOfDay().toDate();
        Date after = DateUtils.addDateDays(now, -7);
        Date yesterday = DateUtils.addDateDays(now, -1);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("MM-dd");
        List<PriceChart> list = systemFeign.selectLineChartByDate(after,yesterday);
        List<String> date = list.stream().map(u -> u.getRateTime().format(dt)).collect(Collectors.toList());
        List<Float> price = list.stream().map(PriceChart::getPrice).collect(Collectors.toList());
        PriceLinechartEntity yestPrice = systemFeign.selectLineChartById(yesterday);
        return R.ok().put("date", date).put("price", price).put("yesterday", yestPrice);
    }
}

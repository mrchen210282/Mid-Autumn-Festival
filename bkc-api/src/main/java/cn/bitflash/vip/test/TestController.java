package cn.bitflash.vip.test;

import cn.bitflash.entity.PriceLinechartEntity;
import cn.bitflash.utils.DateUtils;
import cn.bitflash.utils.R;
import cn.bitflash.vip.system.entity.PriceChart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 获取当天起，前7天的价格
     *
     * @return
     */
    @PostMapping("demo")
    public R getWeekPriceRate() {



        return R.ok();
        //return R.ok().put("date", date).put("price", price).put("yesterday", yestPrice);
    }
}

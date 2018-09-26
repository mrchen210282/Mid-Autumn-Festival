package cn.bitflash.vip.index.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.PriceLinechartEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.index.feign.IndexFeign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bitflash")
@Api(value = "首页贝壳信息", tags = {"首页贝壳信息"})
public class PriceLinechartController {

    @Autowired
    private IndexFeign indexFeign;

    /**
     * 首页贝壳信息
     * @return
     */
    @Login
    @PostMapping("selectPriceLinechart")
    public R selectPriceLinechart(){
        PriceLinechartEntity priceLinechartEntity = indexFeign.selectPriceLinechart();
        if(null != priceLinechartEntity) {
            String rate = String.valueOf(priceLinechartEntity.getRate());
            String[] rates = rate.split(".");
            StringBuffer buf = new StringBuffer();
            if(rates.length > 0) {
                String rate1 = rates[1];
                if(rate1.length() == 1) {
                    buf.append(rate1);
                    buf.append("0%");
                } else if (rate1.length() == 0) {
                    buf.append(rate1);
                    buf.append(".00%");
                }

            }
            priceLinechartEntity.setRateStr(buf.toString());
        }
        return R.ok().put("priceLinechart",priceLinechartEntity);
    }

    public static void main(String[] args) {
        String aa = "10";
        System.out.println(aa.length());
    }
}

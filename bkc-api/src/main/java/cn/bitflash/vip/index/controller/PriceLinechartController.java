package cn.bitflash.vip.index.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.PriceLinechartEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.index.feign.IndexFeign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/bitflash")
@Api(value = "首页贝壳信息", tags = {"首页贝壳信息"})
public class PriceLinechartController {

    @Autowired
    private IndexFeign indexFeign;

    /**
     * 首页贝壳信息
     *
     * @return
     */
    @Login
    @PostMapping("selectPriceLinechart")
    public R selectPriceLinechart() {
        PriceLinechartEntity priceLinechartEntity = indexFeign.selectPriceLinechart();


        Date date = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);

        //查询昨天的汇率进行比较
        Date yesterday = cal.getTime();
        String subtraction = "";
        List<PriceLinechartEntity> priceLinechartList = indexFeign.selectLineChartYesterDayByDate(yesterday);
        if (null != priceLinechartList && priceLinechartList.size() > 0) {
            PriceLinechartEntity priceLinechart = priceLinechartList.get(0);
            float dValue = priceLinechartEntity.getRate() - priceLinechart.getRate();
            if(dValue > 0) {
                subtraction = "+";
            } else if(dValue == 0){
                subtraction = "=";
            } else {
                subtraction = "-";
            }
        } else {
            subtraction = "+";
        }
        priceLinechartEntity.setSubtraction(subtraction);

        if (null != priceLinechartEntity) {
            String rate = String.valueOf(priceLinechartEntity.getRate());
            String[] rates = rate.split("\\.");
            StringBuffer buf = new StringBuffer();
            if (rates.length > 0) {
                String rate1 = rates[1];
                if (rate1.length() == 1) {
                    buf.append(rate1);
                    buf.append("0%");
                } else if (rate1.length() == 0) {
                    buf.append(rate1);
                    buf.append(".00%");
                }
            } else {
                buf.append(rate);
                buf.append("%");
            }
            priceLinechartEntity.setRateStr(buf.toString());
        }
        return R.ok().put("priceLinechart", priceLinechartEntity);
    }

    public static void main(String[] args) {
//        String aa = "0.0";
//        String[] as = aa.split("\\.");
//        System.out.println(as.length);
//        //System.out.println(aa.length());
//
//
//        Date date = new Date();
//        Calendar cal = new GregorianCalendar();
//        cal.setTime(date);
//        cal.add(Calendar.DATE, -1);
//
//        Date yesterday = cal.getTime();
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
//        String str = format.format(yesterday);
//        System.out.println(str);

        float a = 0.8f;
        float b = 0.8f;

        float c = Math.abs(a - b);
        if(Math.abs(a - b) == 0) {
            System.out.println("ddd");
        }
        System.out.println(c);


//        Date date = new Date();//取时间
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(date);
//        calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
//        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = formatter.format(date);
//        System.out.println(dateString);


    }
}

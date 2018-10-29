package cn.bitflash.vip.index.controller;

import cn.bitflash.entity.PriceLinechartEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.index.feign.IndexFeign;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/bitflash")
@Api(value = "首页贝壳信息", tags = {"首页贝壳信息"})
public class PriceLinechartController {

    private final Logger logger = LoggerFactory.getLogger(PriceLinechartController.class);

    @Autowired
    private IndexFeign indexFeign;

    /**
     * 首页贝壳信息
     *
     * @return
     */
    @PostMapping("selectPriceLinechart")
    public R selectPriceLinechart() {

        //取得当天汇率
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        List<PriceLinechartEntity> priceLinechartToday = indexFeign.selectLineChartYesterDayByDate(today);
        PriceLinechartEntity priceLinechartEntity = null;
        int symbol = 1;
        if(null != priceLinechartToday && priceLinechartToday.size() > 0) {
            priceLinechartEntity = priceLinechartToday.get(0);

            //查询昨天的汇率进行比较
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            cal.add(Calendar.DATE, -1);
            Date yesterday = cal.getTime();
            String yester = dateFormat.format(yesterday);
            List<PriceLinechartEntity> priceLinechartList = indexFeign.selectLineChartYesterDayByDate(yester);

            String subtraction = "";

            //如果当天的美元为空，则取最近一天，并且美元价格大于0
            if(priceLinechartEntity.getUs().compareTo(new BigDecimal(0)) <= 0) {
                PriceLinechartEntity priceUs = indexFeign.selectPriceUs();
                priceLinechartEntity.setUsStr(String.format("%.2f",priceUs.getUs()));
            } else {
                priceLinechartEntity.setUsStr(String.format("%.2f",priceLinechartEntity.getUs()));
            }

            //如果当天的人民币为空，则取最近一天，并且人民币价格大于0
            if(priceLinechartEntity.getCny().compareTo(new BigDecimal(0)) <= 0) {
                PriceLinechartEntity priceCny = indexFeign.selectPriceCny();
                priceLinechartEntity.setCnyStr(String.format("%.2f",priceCny.getCny()));
            } else {
                priceLinechartEntity.setCnyStr(String.format("%.2f",priceLinechartEntity.getCny()));
            }

            if (null != priceLinechartList && priceLinechartList.size() > 0) {
                PriceLinechartEntity priceLinechart = priceLinechartList.get(0);

                //计算比率,并保留两位小数
                float ratio = (priceLinechartEntity.getPrice() - priceLinechart.getPrice()) / priceLinechart.getPrice();
                float ratiof = Math.abs(ratio);
                String ratioStr = String.format("%.2f",ratiof);

                //比较今天和昨天是否增加
                float dValue = priceLinechartEntity.getPrice() - priceLinechart.getPrice();
                if(dValue > 0) {
                    subtraction = "+";
                    symbol = 1;

                } else if(dValue == 0){
                    subtraction = "+";
                    symbol = 1;
                } else {
                    subtraction = "-";
                    symbol = 0;
                }
                priceLinechartEntity.setSymbol(symbol);
                priceLinechartEntity.setRateStr(subtraction + ratioStr + "%");
            } else {
                logger.info("无昨天汇率数据！");
                //昨天数据为空则不能计算，默认为0.00
                priceLinechartEntity.setRateStr("+0.00%");
                priceLinechartEntity.setSymbol(symbol);
            }
        } else {
            logger.info("无当天汇率数据！");
            PriceLinechartEntity priceUs = indexFeign.selectPriceUs();
            PriceLinechartEntity priceCny = indexFeign.selectPriceCny();
            //今天数据为空则不能计算，默认为0.00
            priceLinechartEntity = new PriceLinechartEntity();
            priceLinechartEntity.setRateStr("+0.00%");
            priceLinechartEntity.setSymbol(symbol);
            priceLinechartEntity.setUsStr(String.format("%.2f",priceUs.getUs()));

            priceLinechartEntity.setCnyStr(String.format("%.2f",priceCny.getCny()));
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

        float s = 0.12f;
        float r = Math.abs(s);
        System.out.println(r);

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

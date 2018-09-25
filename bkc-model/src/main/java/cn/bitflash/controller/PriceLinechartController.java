package cn.bitflash.controller;

import cn.bitflash.entity.PriceLinechartEntity;
import cn.bitflash.service.PriceLinechartService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author GAOYGUUO
 */
@RestController
public class PriceLinechartController {

    @Autowired
    private PriceLinechartService priceLinechartService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/priceLinechart/selectById")
    public PriceLinechartEntity selectById(@RequestParam("id") String id) {
        PriceLinechartEntity entity = priceLinechartService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/priceLinechart/updateById")
    public void updateById(@RequestBody JSONObject json) throws Exception {
        PriceLinechartEntity entity = (PriceLinechartEntity) JSONObject.parseObject(json.toString(), PriceLinechartEntity.class);
        priceLinechartService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/priceLinechart/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        PriceLinechartEntity entity = (PriceLinechartEntity) JSONObject.parseObject(json.toString(), PriceLinechartEntity.class);
        priceLinechartService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/priceLinechart/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        priceLinechartService.deleteById(id);
    }

    /**
     * selectLineChartByDate
     * @param after
     * @param yesterday
     * @return
     */
    @PostMapping("/inner/priceLinechart/selectLineChartByDate")
    public List<PriceLinechartEntity> selectLineChartByDate(@RequestParam("after") Date after, @RequestParam("yesterday") Date yesterday){
        List<PriceLinechartEntity> priceLinechartEntities = priceLinechartService.selectList(new EntityWrapper<PriceLinechartEntity>().between("rate_time", after, yesterday).orderBy("rate_time"));
        return priceLinechartEntities;
    }

    /**
     *selectLineChartById
     */
    @PostMapping("/inner/priceLinechart/selectLineChartById")
    public PriceLinechartEntity selectLineChartById(@RequestParam("date")Date date){
        PriceLinechartEntity priceLinechartEntity = priceLinechartService.selectById(date);
        return priceLinechartEntity;
    }
}

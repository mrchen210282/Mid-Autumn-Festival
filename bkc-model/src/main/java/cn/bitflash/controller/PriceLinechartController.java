package cn.bitflash.controller;


import cn.bitflash.entity.PriceLinechartEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.PriceLinechartService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author GAOYUGUO
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {

        Date date = json.getDate("rateTime");
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);

        PriceLinechartEntity entity = new PriceLinechartEntity();
        entity.setRateTime(localDateTime);
        entity.setCny(json.getInteger("cny"));
        entity.setBkc(json.getFloat("bkc"));
        entity.setRate(json.getFloat("rate"));
        entity.setPrice(json.getFloat("price"));
        priceLinechartService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/priceLinechart/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        Date date = json.getDate("rateTime");
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);

        PriceLinechartEntity entity = new PriceLinechartEntity();
        entity.setRateTime(localDateTime);
        entity.setCny(json.getInteger("cny"));
        entity.setBkc(json.getFloat("bkc"));
        entity.setRate(json.getFloat("rate"));
        entity.setPrice(json.getFloat("price"));
        priceLinechartService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/priceLinechart/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        priceLinechartService.deleteById(id);
    }

}

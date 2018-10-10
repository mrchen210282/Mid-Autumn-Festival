package cn.bitflash.controller;

import cn.bitflash.entity.BuyPoundageEntity;
import cn.bitflash.entity.TradePoundageEntity;
import cn.bitflash.service.BuyPoundageService;
import cn.bitflash.service.TradePoundageService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class TradePoundageController {

    @Autowired
    private TradePoundageService tradePoundageService;

    /**
     * selectTradePoundageById
     *
     * @return
     */
    @PostMapping("/inner/tradePoundage/selectById")
    public TradePoundageEntity selectTradePoundageById(@RequestParam("id") String id) {
        TradePoundageEntity entity = tradePoundageService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/tradePoundage/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        TradePoundageEntity entity = (TradePoundageEntity) JSONObject.parseObject(json.toString(), TradePoundageEntity.class);
        return tradePoundageService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/tradePoundage/insertTradePoundage")
    public boolean insertTradePoundage(@RequestBody JSONObject json) throws Exception {
        TradePoundageEntity entity = (TradePoundageEntity) JSONObject.parseObject(json.toString(), TradePoundageEntity.class);
        return tradePoundageService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/tradePoundage/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return tradePoundageService.deleteById(id);
    }

}

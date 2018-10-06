package cn.bitflash.controller;

import cn.bitflash.entity.BuyPoundageEntity;
import cn.bitflash.service.BuyPoundageService;
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
public class BuyPoundageController {

    @Autowired
    private BuyPoundageService buyPoundageService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/buyPoundage/selectById")
    public BuyPoundageEntity selectById(@RequestParam("id") String id) {
        BuyPoundageEntity entity = buyPoundageService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/buyPoundage/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        BuyPoundageEntity entity = (BuyPoundageEntity) JSONObject.parseObject(json.toString(), BuyPoundageEntity.class);
        return buyPoundageService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/buyPoundage/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        BuyPoundageEntity entity = (BuyPoundageEntity) JSONObject.parseObject(json.toString(), BuyPoundageEntity.class);
        return buyPoundageService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/buyPoundage/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return buyPoundageService.deleteById(id);
    }

}

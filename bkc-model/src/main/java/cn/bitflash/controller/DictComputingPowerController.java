package cn.bitflash.controller;


import cn.bitflash.entity.DictComputingPowerEntity;
import cn.bitflash.service.DictComputingPowerService;
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
public class DictComputingPowerController {

    @Autowired
    private DictComputingPowerService dictComputingPowerService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/dictComputingPower/selectById")
    public DictComputingPowerEntity selectById(@RequestParam("id") String id) {
        DictComputingPowerEntity entity = dictComputingPowerService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/dictComputingPower/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        DictComputingPowerEntity entity = (DictComputingPowerEntity) JSONObject.parseObject(json.toString(), DictComputingPowerEntity.class);
        return dictComputingPowerService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/dictComputingPower/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        DictComputingPowerEntity entity = (DictComputingPowerEntity) JSONObject.parseObject(json.toString(), DictComputingPowerEntity.class);
        return dictComputingPowerService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/dictComputingPower/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return dictComputingPowerService.deleteById(id);
    }

}

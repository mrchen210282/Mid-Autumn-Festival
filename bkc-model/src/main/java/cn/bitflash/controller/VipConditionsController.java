
package cn.bitflash.controller;

import cn.bitflash.entity.VipConditionsEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.VipConditionsService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYUGUO
 */
@RestController
public class VipConditionsController {

    @Autowired
    private VipConditionsService vipConditionsService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/vipConditions/selectById")
    public VipConditionsEntity selectById(@RequestParam("id") String id) {
        VipConditionsEntity entity = vipConditionsService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/vipConditions/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        VipConditionsEntity entity = new VipConditionsEntity();
        entity.setLevel(json.getString("level"));
        entity.setPower(json.getBigDecimal("power"));
        entity.setBkcount(json.getInteger("bkcount"));
        entity.setRemark(json.getString("remark"));
        entity.setState(json.getInteger("state"));
        vipConditionsService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/vipConditions/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        VipConditionsEntity entity = new VipConditionsEntity();
        entity.setLevel(json.getString("level"));
        entity.setPower(json.getBigDecimal("power"));
        entity.setBkcount(json.getInteger("bkcount"));
        entity.setRemark(json.getString("remark"));
        entity.setState(json.getInteger("state"));
        vipConditionsService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/vipConditions/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        vipConditionsService.deleteById(id);
    }

}

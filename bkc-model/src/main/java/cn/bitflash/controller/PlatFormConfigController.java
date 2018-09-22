package cn.bitflash.controller;

import cn.bitflash.entity.PlatformConfigEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.PlatFormConfigService;
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
public class PlatFormConfigController {

    @Autowired
    private PlatFormConfigService platFornConfigService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/platFormConfig/selectById")
    public PlatformConfigEntity selectById(@RequestParam("id") Integer id) {
        PlatformConfigEntity entity = platFornConfigService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/platFormConfig/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        PlatformConfigEntity entity = new PlatformConfigEntity();
        entity.setId(json.getInteger("id"));
        entity.setUnit(json.getString("unit"));
        entity.setConfigName(json.getString("configName"));
        entity.setConfigValue(json.getString("configValue"));
        entity.setCreateTime(json.getDate("createTime"));
        entity.setUpdateTime(json.getDate("updateTime"));
        platFornConfigService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/platFormConfig/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        PlatformConfigEntity entity = new PlatformConfigEntity();
        entity.setId(json.getInteger("id"));
        entity.setUnit(json.getString("unit"));
        entity.setConfigName(json.getString("configName"));
        entity.setConfigValue(json.getString("configValue"));
        entity.setCreateTime(json.getDate("createTime"));
        entity.setUpdateTime(json.getDate("updateTime"));
        platFornConfigService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/platFormConfig/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") Integer id) {
        platFornConfigService.deleteById(id);
    }

    /**
     * getVal
     *
     * @param key
     * @return
     */
    @PostMapping("/inner/platFormConfig/getVal")
    String getVal(@RequestParam("key") String key){
        String val = platFornConfigService.getVal(key);
        return val;
    }

}

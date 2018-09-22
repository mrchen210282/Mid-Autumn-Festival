package cn.bitflash.controller;


import cn.bitflash.entity.SystemParamEntity;
import cn.bitflash.service.SystemParamService;
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
public class SystemParamController {

    @Autowired
    private SystemParamService systemParamService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/systemParam/selectById")
    public SystemParamEntity selectById(@RequestParam("id") String id) {
        SystemParamEntity entity = systemParamService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/systemParam/updateById")
    public void updateById(@RequestBody JSONObject json) throws Exception {
        SystemParamEntity entity = (SystemParamEntity) JSONObject.parseObject(json.toString(), SystemParamEntity.class);
        systemParamService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/systemParam/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        SystemParamEntity entity = (SystemParamEntity) JSONObject.parseObject(json.toString(), SystemParamEntity.class);
        systemParamService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/systemParam/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        systemParamService.deleteById(id);
    }

}

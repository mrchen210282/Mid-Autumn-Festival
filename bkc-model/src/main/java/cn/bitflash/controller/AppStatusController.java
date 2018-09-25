package cn.bitflash.controller;

import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.service.AppStatusService;
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
public class AppStatusController {

    @Autowired
    private AppStatusService appStatusService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/appStatus/selectById")
    public AppStatusEntity selectById(@RequestParam("id") String id) {
        AppStatusEntity entity = appStatusService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/appStatus/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        AppStatusEntity entity = (AppStatusEntity) JSONObject.parseObject(json.toString(), AppStatusEntity.class);
        return appStatusService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/appStatus/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        AppStatusEntity entity = (AppStatusEntity) JSONObject.parseObject(json.toString(), AppStatusEntity.class);
        return appStatusService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/appStatus/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return appStatusService.deleteById(id);
    }

}

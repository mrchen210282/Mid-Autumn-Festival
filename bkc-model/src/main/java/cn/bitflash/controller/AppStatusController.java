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
    public void updateById(@RequestBody JSONObject json) {
        AppStatusEntity entity = new AppStatusEntity();
        entity.setAppid(json.getString("appId"));
        entity.setVersion(json.getString("version"));
        entity.setUrl(json.getString("url"));
        entity.setTitle(json.getString("title"));
        entity.setNote(json.getString("note"));
        appStatusService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/appStatus/insert")
    public void insert(@RequestBody JSONObject json) {
        AppStatusEntity entity = new AppStatusEntity();
        entity.setAppid(json.getString("appId"));
        entity.setUrl(json.getString("url"));
        entity.setTitle(json.getString("title"));
        entity.setNote(json.getString("note"));
        entity.setVersion(json.getString("version"));
        appStatusService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/appStatus/deleteById")
    public void deleteById(@RequestParam("id") String id) {
        appStatusService.deleteById(id);
    }

}

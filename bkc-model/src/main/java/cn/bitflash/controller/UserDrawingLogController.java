package cn.bitflash.controller;

import cn.bitflash.entity.UserDrawingLogEntity;
import cn.bitflash.service.UserDrawingLogService;
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
public class UserDrawingLogController {

    @Autowired
    private UserDrawingLogService userDrawingLogService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userDrawingLog/selectById")
    public UserDrawingLogEntity selectById(@RequestParam("id") String id) {
        UserDrawingLogEntity entity = userDrawingLogService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userDrawingLog/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserDrawingLogEntity entity = (UserDrawingLogEntity) JSONObject.parseObject(json.toString(), UserDrawingLogEntity.class);
        return userDrawingLogService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userDrawingLog/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserDrawingLogEntity entity = (UserDrawingLogEntity) JSONObject.parseObject(json.toString(), UserDrawingLogEntity.class);
        return userDrawingLogService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userDrawingLog/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userDrawingLogService.deleteById(id);
    }

}

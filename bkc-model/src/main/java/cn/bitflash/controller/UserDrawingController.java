package cn.bitflash.controller;

import cn.bitflash.entity.UserDrawingEntity;
import cn.bitflash.service.UserDrawingService;
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
public class UserDrawingController {

    @Autowired
    private UserDrawingService userDrawingService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userDrawing/selectById")
    public UserDrawingEntity selectById(@RequestParam("id") String id) {
        UserDrawingEntity entity = userDrawingService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userDrawing/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserDrawingEntity entity = (UserDrawingEntity) JSONObject.parseObject(json.toString(), UserDrawingEntity.class);
        return userDrawingService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userDrawing/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserDrawingEntity entity = (UserDrawingEntity) JSONObject.parseObject(json.toString(), UserDrawingEntity.class);
        return userDrawingService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userDrawing/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userDrawingService.deleteById(id);
    }

}

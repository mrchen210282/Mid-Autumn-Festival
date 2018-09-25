package cn.bitflash.controller;

import cn.bitflash.entity.UserDrawingInfoEntity;
import cn.bitflash.service.UserDrawingInfoService;
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
public class UserDrawingInfoController {

    @Autowired
    private UserDrawingInfoService userDrawingInfoService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userDrawingInfo/selectById")
    public UserDrawingInfoEntity selectById(@RequestParam("id") String id) {
        UserDrawingInfoEntity entity = userDrawingInfoService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userDrawingInfo/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserDrawingInfoEntity entity = (UserDrawingInfoEntity) JSONObject.parseObject(json.toString(), UserDrawingInfoEntity.class);
        return userDrawingInfoService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userDrawingInfo/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserDrawingInfoEntity entity = (UserDrawingInfoEntity) JSONObject.parseObject(json.toString(), UserDrawingInfoEntity.class);
        return userDrawingInfoService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userDrawingInfo/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userDrawingInfoService.deleteById(id);
    }

}

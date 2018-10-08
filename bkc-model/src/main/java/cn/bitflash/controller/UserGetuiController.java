package cn.bitflash.controller;

import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.entity.UserGetuiEntity;
import cn.bitflash.service.AppStatusService;
import cn.bitflash.service.UserGetuiService;
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
public class UserGetuiController {

    @Autowired
    private UserGetuiService userGetuiService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userGetui/selectById")
    public UserGetuiEntity selectById(@RequestParam("id") String id) {
        UserGetuiEntity entity = userGetuiService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userGetui/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserGetuiEntity entity = (UserGetuiEntity) JSONObject.parseObject(json.toString(), UserGetuiEntity.class);
        return userGetuiService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userGetui/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserGetuiEntity entity = (UserGetuiEntity) JSONObject.parseObject(json.toString(), UserGetuiEntity.class);
        return userGetuiService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userGetui/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userGetuiService.deleteById(id);
    }

    /**
     * insertOrUpdate
     *
     * @return
     */
    @PostMapping("/inner/userGetui/insertOrUpdate")
    public boolean deleteById(@RequestBody JSONObject json) throws Exception {
        UserGetuiEntity entity = (UserGetuiEntity) JSONObject.parseObject(json.toString(), UserGetuiEntity.class);
        return userGetuiService.insertOrUpdate(entity);
    }

}

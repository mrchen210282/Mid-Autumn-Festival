package cn.bitflash.controller;

import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.service.UserSecretService;
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
public class UserSecretController {

    @Autowired
    private UserSecretService userSecretService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userSecretService/selectById")
    public UserSecretEntity selectById(@RequestParam("id") String id) {
        UserSecretEntity entity = userSecretService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userSecretService/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserSecretEntity entity = (UserSecretEntity) JSONObject.parseObject(json.toString(), UserSecretEntity.class);
        return userSecretService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userSecretService/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserSecretEntity entity = (UserSecretEntity) JSONObject.parseObject(json.toString(), UserSecretEntity.class);
        return userSecretService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userSecretService/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userSecretService.deleteById(id);
    }

}

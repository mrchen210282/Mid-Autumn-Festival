package cn.bitflash.controller;


import cn.bitflash.entity.UserDigitalAssetsEntity;
import cn.bitflash.service.UserDigitalAssetsService;
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
public class UserDigitalAssetsController {

    @Autowired
    private UserDigitalAssetsService userDigitalAssetsService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userDigitalAssets/selectById")
    public UserDigitalAssetsEntity selectById(@RequestParam("id") String id) {
        UserDigitalAssetsEntity entity = userDigitalAssetsService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userDigitalAssets/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserDigitalAssetsEntity entity = (UserDigitalAssetsEntity) JSONObject.parseObject(json.toString(), UserDigitalAssetsEntity.class);
        return userDigitalAssetsService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userDigitalAssets/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserDigitalAssetsEntity entity = (UserDigitalAssetsEntity) JSONObject.parseObject(json.toString(), UserDigitalAssetsEntity.class);
        return userDigitalAssetsService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userDigitalAssets/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userDigitalAssetsService.deleteById(id);
    }

}

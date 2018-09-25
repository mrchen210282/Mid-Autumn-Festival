package cn.bitflash.controller;


import cn.bitflash.entity.UserCashAssetsEntity;
import cn.bitflash.service.UserCashAssetsService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserCashAssetsController {

    @Autowired
    private UserCashAssetsService userCashAssetsService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userCashAssets/selectById")
    public UserCashAssetsEntity selectById(@RequestParam("id") String id) {
        UserCashAssetsEntity entity = userCashAssetsService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userCashAssets/updateById")
    public void updateById(@RequestBody JSONObject json) throws Exception {
        UserCashAssetsEntity entity = (UserCashAssetsEntity) JSONObject.parseObject(json.toString(), UserCashAssetsEntity.class);
        userCashAssetsService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userCashAssets/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        UserCashAssetsEntity entity = (UserCashAssetsEntity) JSONObject.parseObject(json.toString(), UserCashAssetsEntity.class);
        userCashAssetsService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userCashAssets/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        userCashAssetsService.deleteById(id);
    }


    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userCashAssets/selectById")
    public Map<String,Object> getIndexAssets(@RequestParam("id") String id) {
        Map<String,Object> map = new HashMap<String,Object>();


        return map;
    }

}

package cn.bitflash.controller;


import cn.bitflash.entity.DictComputingPowerEntity;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.service.DictComputingPowerService;
import cn.bitflash.service.UserInfoService;
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
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userInfo/selectById")
    public UserInfoEntity selectById(@RequestParam("id") String id) {
        UserInfoEntity entity = userInfoService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userInfo/updateById")
    public void updateById(@RequestBody JSONObject json) throws Exception {
        UserInfoEntity entity = (UserInfoEntity) JSONObject.parseObject(json.toString(), UserInfoEntity.class);
        userInfoService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userInfo/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        UserInfoEntity entity = (UserInfoEntity) JSONObject.parseObject(json.toString(), UserInfoEntity.class);
        userInfoService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userInfo/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        userInfoService.deleteById(id);
    }

}

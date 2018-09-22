package cn.bitflash.controller;


import cn.bitflash.entity.UserInfoConfigEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserInfoConfigService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYUGUO
 */
@RestController
public class UserInfoConfigController {

    @Autowired
    private UserInfoConfigService userInfoConfigService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userInfoConfig/selectById")
    public UserInfoConfigEntity selectById(@RequestParam("id") Integer id) {
        UserInfoConfigEntity entity = userInfoConfigService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userInfoConfig/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserInfoConfigEntity entity = new UserInfoConfigEntity();
        entity.setId(json.getInteger("id"));
        entity.setMin(json.getInteger("min"));
        entity.setShowPower(json.getString("showPower"));
        entity.setGiveRate(json.getDouble("giveRate"));
        entity.setShowGiveRate(json.getString("showGiveRate"));
        userInfoConfigService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userInfoConfig/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserInfoConfigEntity entity = new UserInfoConfigEntity();
        entity.setId(json.getInteger("id"));
        entity.setMin(json.getInteger("min"));
        entity.setShowPower(json.getString("showPower"));
        entity.setGiveRate(json.getDouble("giveRate"));
        entity.setShowGiveRate(json.getString("showGiveRate"));
        userInfoConfigService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userInfoConfig/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") Integer id) {
        userInfoConfigService.deleteById(id);
    }

}

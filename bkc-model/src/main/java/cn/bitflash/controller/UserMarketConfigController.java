package cn.bitflash.controller;

import cn.bitflash.entity.BuyPoundageEntity;
import cn.bitflash.entity.UserMarketConfigEntity;
import cn.bitflash.service.BuyPoundageService;
import cn.bitflash.service.UserMarketConfigService;
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
public class UserMarketConfigController {

    @Autowired
    private UserMarketConfigService userMarketConfigService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userMarketConfig/selectById")
    public UserMarketConfigEntity selectById(@RequestParam("id") int id) {
        UserMarketConfigEntity entity = userMarketConfigService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userMarketConfig/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserMarketConfigEntity entity = (UserMarketConfigEntity) JSONObject.parseObject(json.toString(), UserMarketConfigEntity.class);
        return userMarketConfigService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userMarketConfig/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserMarketConfigEntity entity = (UserMarketConfigEntity) JSONObject.parseObject(json.toString(), UserMarketConfigEntity.class);
        return userMarketConfigService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userMarketConfig/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userMarketConfigService.deleteById(id);
    }

}

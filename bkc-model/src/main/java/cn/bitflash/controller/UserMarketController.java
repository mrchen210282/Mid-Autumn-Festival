package cn.bitflash.controller;


import cn.bitflash.entity.UserMarketEntity;
import cn.bitflash.service.UserMarketService;
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
public class UserMarketController {

    @Autowired
    private UserMarketService userMarketService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userMark/selectById")
    public UserMarketEntity selectById(@RequestParam("id") String id) {
        UserMarketEntity entity = userMarketService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userMark/updateById")
    public void updateById(@RequestBody JSONObject json) throws Exception {
        UserMarketEntity entity = (UserMarketEntity) JSONObject.parseObject(json.toString(), UserMarketEntity.class);
        userMarketService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userMark/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        UserMarketEntity entity = (UserMarketEntity) JSONObject.parseObject(json.toString(), UserMarketEntity.class);
        userMarketService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userMark/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        userMarketService.deleteById(id);
    }

}

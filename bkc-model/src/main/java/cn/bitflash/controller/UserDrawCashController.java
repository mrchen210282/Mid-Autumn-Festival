package cn.bitflash.controller;


import cn.bitflash.entity.UserDrawCashEntity;
import cn.bitflash.service.UserDrawCashService;
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
public class UserDrawCashController {

    @Autowired
    private UserDrawCashService userDrawCashService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userDrawCash/selectById")
    public UserDrawCashEntity selectById(@RequestParam("id") String id) {
        UserDrawCashEntity entity = userDrawCashService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userDrawCash/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserDrawCashEntity entity = (UserDrawCashEntity) JSONObject.parseObject(json.toString(), UserDrawCashEntity.class);
        return userDrawCashService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userDrawCash/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserDrawCashEntity entity = (UserDrawCashEntity) JSONObject.parseObject(json.toString(), UserDrawCashEntity.class);
        return userDrawCashService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userDrawCash/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userDrawCashService.deleteById(id);
    }

}

package cn.bitflash.controller;

import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.entity.UserSharedIncomePoolEntity;
import cn.bitflash.service.AppStatusService;
import cn.bitflash.service.UserSharedIncomePoolService;
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
public class UserSharedIncomePoolController {

    @Autowired
    private UserSharedIncomePoolService userSharedIncomePoolService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userSharedIncomePool/selectById")
    public UserSharedIncomePoolEntity selectById(@RequestParam("id") String id) {
        UserSharedIncomePoolEntity entity = userSharedIncomePoolService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userSharedIncomePool/updateById")
    public void updateById(@RequestBody JSONObject json) throws Exception {
        UserSharedIncomePoolEntity entity = (UserSharedIncomePoolEntity) JSONObject.parseObject(json.toString(), UserSharedIncomePoolEntity.class);
        userSharedIncomePoolService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userSharedIncomePool/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        UserSharedIncomePoolEntity entity = (UserSharedIncomePoolEntity) JSONObject.parseObject(json.toString(), UserSharedIncomePoolEntity.class);
        userSharedIncomePoolService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userSharedIncomePool/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        userSharedIncomePoolService.deleteById(id);
    }

}

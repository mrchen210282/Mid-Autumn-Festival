package cn.bitflash.controller;


import cn.bitflash.entity.UserMarkEntity;
import cn.bitflash.service.UserMarkService;
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
public class UserMarkController {

    @Autowired
    private UserMarkService userMarkService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userMark/selectById")
    public UserMarkEntity selectById(@RequestParam("id") String id) {
        UserMarkEntity entity = userMarkService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userMark/updateById")
    public void updateById(@RequestBody JSONObject json) throws Exception {
        UserMarkEntity entity = (UserMarkEntity) JSONObject.parseObject(json.toString(), UserMarkEntity.class);
        userMarkService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userMark/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        UserMarkEntity entity = (UserMarkEntity) JSONObject.parseObject(json.toString(), UserMarkEntity.class);
        userMarkService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userMark/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        userMarkService.deleteById(id);
    }

}

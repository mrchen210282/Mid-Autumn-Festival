package cn.bitflash.controller;


import cn.bitflash.entity.UserRelationEntity;
import cn.bitflash.service.UserRelationService;
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
public class UserRelationController {

    @Autowired
    private UserRelationService userRelationService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userRelation/selectById")
    public UserRelationEntity selectById(@RequestParam("id") String id) {
        UserRelationEntity entity = userRelationService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userRelation/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserRelationEntity entity = (UserRelationEntity) JSONObject.parseObject(json.toString(), UserRelationEntity.class);
        return userRelationService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userRelation/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserRelationEntity entity = (UserRelationEntity) JSONObject.parseObject(json.toString(), UserRelationEntity.class);
        return userRelationService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userRelation/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userRelationService.deleteById(id);
    }

}

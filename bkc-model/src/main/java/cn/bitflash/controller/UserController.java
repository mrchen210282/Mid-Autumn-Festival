package cn.bitflash.controller;


import cn.bitflash.entity.UserEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/user/selectById")
    public UserEntity selectById(@RequestParam("id") String id) {
        UserEntity entity = userService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/user/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserEntity entity = new UserEntity();
        entity.setMobile(json.getString("mobile"));
        entity.setUuid(json.getString("uuid"));
        entity.setPassword(json.getString("password"));
        entity.setUid(json.getString("uid"));
        userService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/user/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserEntity entity = new UserEntity();
        entity.setMobile(json.getString("mobile"));
        entity.setUuid(json.getString("uuid"));
        entity.setUid(json.getString("uid"));
        entity.setPassword(json.getString("password"));
        userService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/user/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        userService.deleteById(id);
    }

    /**
     * selectByUuid
     */
    @PostMapping("/inner/user/selectByUuid")
    JSONObject selectByUuid(@RequestParam("uuid") String uuid){
        UserEntity entity = userService.selectOne(new EntityWrapper<UserEntity>().eq("uuid",uuid));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", entity.getUuid());
        jsonObject.put("uid", entity.getUid());
        jsonObject.put("password", entity.getPassword());
        jsonObject.put("mobile", entity.getMobile());
        return jsonObject;
    }
}

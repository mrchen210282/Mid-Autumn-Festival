package cn.bitflash.controller;


import cn.bitflash.entity.UserPayImgEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserPayImgService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GAOYUGUO
 */
@RestController
public class UserPayImgController {

    @Autowired
    private UserPayImgService userPayImgService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userPayUrl/selectById")
    public UserPayImgEntity selectById(@RequestParam("id") String id) {
        UserPayImgEntity entity = userPayImgService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userPayUrl/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserPayImgEntity entity = new UserPayImgEntity();
        entity.setUid(json.getString("uid"));
        entity.setImgType(json.getString("imgType"));
        entity.setImgUrl(json.getString("imgUrl"));
        entity.setMobile(json.getString("mobile"));
        entity.setAccount(json.getString("accountName"));
        entity.setAccount(json.getString("account"));
        userPayImgService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userPayUrl/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserPayImgEntity entity = new UserPayImgEntity();
        entity.setUid(json.getString("uid"));
        entity.setImgType(json.getString("imgType"));
        entity.setImgUrl(json.getString("imgUrl"));
        entity.setMobile(json.getString("mobile"));
        entity.setAccount(json.getString("accountName"));
        entity.setAccount(json.getString("account"));
        userPayImgService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userPayUrl/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        userPayImgService.deleteById(id);
    }

    /**
     * selectList
     *
     * @return
     */
    @PostMapping("/inner/userPayUrl/selectList")
    public List<UserPayImgEntity> selectList(@RequestParam("id") String id) {
        List<UserPayImgEntity> entities = userPayImgService.selectList(new EntityWrapper<UserPayImgEntity>().eq("uid",id));
        return entities;
    }

}

package cn.bitflash.controller;


import cn.bitflash.entity.UserPayPwdEntity;
import cn.bitflash.entity.UserPayUrlEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserPayPwdService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserPayPwdController {

    @Autowired
    private UserPayPwdService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserPayPwdEntity selectOne(Map<String, Object> param) {
        List<UserPayPwdEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserPayPwdEntity entity = entityList.get(0);
            return entity;
        }
        return null;
    }

    /**
     * selectUid
     *
     * @param uid
     * @return
     */
    @PostMapping("inner/userPayPwd/selectUid")
    public UserPayPwdEntity selectUid(String uid) {
        UserPayPwdEntity userPayPwdEntity = service.selectOne(new EntityWrapper<UserPayPwdEntity>().eq("uid",uid));
        return userPayPwdEntity;
    }

    /**
     * updateById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(UserPayPwdEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserPayPwdEntity entity) {
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(int id) {
        service.deleteById(id);
    }

}

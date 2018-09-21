package cn.bitflash.controller;


import cn.bitflash.entity.LoginForm;
import cn.bitflash.entity.UserEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserController {

    @Autowired
    private UserService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserEntity selectOne(Map<String, Object> param) {
        List<UserEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserEntity entity = entityList.get(0);
            return entity;
        }
        return null;
    }

    /**
     * updateById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(UserEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserEntity entity) {
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(String id) {
        service.deleteById(id);
    }

    /**
     * queryByMobile
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public UserEntity queryByMobile(String mobile) {
        UserEntity userEntity = service.queryByMobile(mobile);
        return userEntity;
    }

    /**
     * login
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public Map<String, Object> login(LoginForm form) {
        Map<String, Object> map = service.login(form);
        return map;
    }

}

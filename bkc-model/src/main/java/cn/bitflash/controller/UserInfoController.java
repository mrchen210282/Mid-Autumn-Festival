package cn.bitflash.controller;


import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserInfoService;
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
public class UserInfoController {

    @Autowired
    private UserInfoService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserInfoEntity selectOne(Map<String, Object> param) {
        List<UserInfoEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserInfoEntity entity = entityList.get(0);
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
    public void updateById(UserInfoEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserInfoEntity entity) {
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

}

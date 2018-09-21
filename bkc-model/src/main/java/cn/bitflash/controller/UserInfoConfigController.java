package cn.bitflash.controller;


import cn.bitflash.entity.UserInfoConfigEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserInfoConfigService;
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
public class UserInfoConfigController {

    @Autowired
    private UserInfoConfigService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserInfoConfigEntity selectOne(Map<String, Object> param) {
        List<UserInfoConfigEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserInfoConfigEntity entity = entityList.get(0);
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
    public void updateById(UserInfoConfigEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserInfoConfigEntity entity) {
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

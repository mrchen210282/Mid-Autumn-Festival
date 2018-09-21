package cn.bitflash.controller;


import cn.bitflash.entity.UserBrokerageEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserBrokerageService;
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
public class UserBrokerageController {

    @Autowired
    private UserBrokerageService service;

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/userBrokerage/selectOne")
    public UserBrokerageEntity selectById(int id) {
        UserBrokerageEntity userBrokerageEntity = service.selectById(id);
        return userBrokerageEntity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userBrokerage/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(UserBrokerageEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserBrokerageEntity entity) {
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

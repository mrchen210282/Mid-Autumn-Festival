package cn.bitflash.controller;


import cn.bitflash.entity.UserBuyHistoryBean;
import cn.bitflash.entity.UserBuyHistoryEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserBuyHistoryService;
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
public class UserBuyHistoryController {

    @Autowired
    private UserBuyHistoryService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */
    @PostMapping("/inner/userBuyHistory/selectOne")
    public UserBuyHistoryEntity selectOne(Map<String, Object> param) {
        List<UserBuyHistoryEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserBuyHistoryEntity entity = entityList.get(0);
            return entity;
        }
        return null;
    }

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/userBuyHistory/selectById")
    public UserBuyHistoryEntity selectById(String id) {
        UserBuyHistoryEntity userBuyHistoryEntity = service.selectById(id);
        return userBuyHistoryEntity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userBuyHistory/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(UserBuyHistoryEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userBuyHistory/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserBuyHistoryEntity entity) {
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userBuyHistory/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(String id) {
        service.deleteById(id);
    }

    /**
     * selectBuyHistory
     *
     * @return
     */
    @PostMapping("/inner/userBuyHistory/selectBuyHistory")
    public UserBuyHistoryBean selectBuyHistory(String id) {
        UserBuyHistoryBean userBuyHistoryBean = service.selectBuyHistory(id);
        return userBuyHistoryBean;
    }

}

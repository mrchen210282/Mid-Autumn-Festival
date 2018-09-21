package cn.bitflash.controller;


import cn.bitflash.entity.UserTradeHistoryBean;
import cn.bitflash.entity.UserTradeHistoryEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserTradeHistoryService;
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
public class UserTradeHistoryController {

    @Autowired
    private UserTradeHistoryService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserTradeHistoryEntity selectOne(Map<String, Object> param) {
        List<UserTradeHistoryEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserTradeHistoryEntity entity = entityList.get(0);
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
    public void updateById(UserTradeHistoryEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserTradeHistoryEntity entity) {
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
     * updateUserTradeHistory
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateUserTradeHistory(UserTradeHistoryEntity uesrTradeHistory) {
        service.updateUserTradeHistory(uesrTradeHistory);
    }

    /**
     * selectTradeHistory
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public List<UserTradeHistoryBean> selectTradeHistory(Map<String, Object> map) {
        List<UserTradeHistoryBean> userTradeHistoryBeans = service.selectTradeHistory(map);
        return userTradeHistoryBeans;
    }

    /**
     * selectTradeHistoryIncome
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public Map<String, Object> selectTradeHistoryIncome(Map<String, Object> param) {
        Map<String, Object> map = service.selectTradeHistoryIncome(param);
        return map;
    }

    /**
     * insertUserTradeHistory
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insertUserTradeHistory(UserTradeHistoryEntity uesrTradeHistory) {
        service.insertUserTradeHistory(uesrTradeHistory);
    }

}

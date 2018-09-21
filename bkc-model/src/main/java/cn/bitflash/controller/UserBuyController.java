package cn.bitflash.controller;


import cn.bitflash.entity.UserBuyBean;
import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyMessageBean;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserBuyService;
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
public class UserBuyController {

    @Autowired
    private UserBuyService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */
    @PostMapping("/inner/userBuy/selectOne")
    public UserBuyEntity selectOne(Map<String, Object> param) {
        List<UserBuyEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserBuyEntity entity = entityList.get(0);
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
    @PostMapping("/inner/userBuy/selectById")
    public UserBuyEntity selectById(String id) {
        UserBuyEntity userBuyEntity = service.selectById(id);
        return userBuyEntity;
    }


    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userBuy/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(UserBuyEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userBuy/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserBuyEntity entity) {
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userBuy/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(String id) {
        service.deleteById(id);
    }

    /**
     * getBuyMessage
     *
     * @return
     */
    @PostMapping("/inner/userBuy/getBuyMessage")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public List<UserBuyMessageBean> getBuyMessage(String uid, Integer pages) {
        List<UserBuyMessageBean> userBuyMessageBeans = service.getBuyMessage(uid, pages);
        return userBuyMessageBeans;
    }

    /**
     * getNumToPaging
     *
     * @return
     */
    @PostMapping("/inner/userBuy/getNumToPaging")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public Integer getNumToPaging() {
        int num = service.getNumToPaging();
        return num;
    }

    /**
     * selectBuyList
     *
     * @return
     */
    @PostMapping("/inner/userBuy/selectBuyList")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public List<UserBuyBean> selectBuyList(String uid, Integer pages) {
        List<UserBuyBean> userBuyBeans = service.selectBuyList(uid, pages);
        return userBuyBeans;
    }

    /**
     * selectUserBuyOwnCount
     *
     * @return
     */
    @PostMapping("/inner/userBuy/selectUserBuyOwnCount")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public Integer selectUserBuyOwnCount(String uid) {
        int num = service.selectUserBuyOwnCount(uid);
        return num;
    }

    /**
     * selectAppealList
     *
     * @return
     */
    @PostMapping("/inner/userBuy/selectAppealList")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public List<UserBuyBean> selectAppealList(String uid, Integer pages) {
        List<UserBuyBean> userBuyBeans = service.selectAppealList(uid, pages);
        return userBuyBeans;
    }

    /**
     * selectAppealCount
     *
     * @return
     */
    @PostMapping("/inner/userBuy/selectAppealCount")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public Integer selectAppealCount(String uid) {
        int num = service.selectAppealCount(uid);
        return num;
    }
}

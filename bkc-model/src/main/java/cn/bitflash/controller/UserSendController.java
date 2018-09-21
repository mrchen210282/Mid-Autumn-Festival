package cn.bitflash.controller;

import cn.bitflash.entity.UserSendEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserSendService;
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
public class UserSendController {

    @Autowired
    private UserSendService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserSendEntity selectOne(Map<String, Object> param) {
        List<UserSendEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserSendEntity entity = entityList.get(0);
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
    public void updateById(UserSendEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserSendEntity entity) {
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
     * selectaccount
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public List<UserSendEntity> selectaccount(String send_uid, Integer pages) {
        List<UserSendEntity> userSendEntities = service.selectaccount(send_uid, pages);
        return userSendEntities;
    }

    /**
     * selectaccept
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public List<UserSendEntity> selectaccept(String send_uid, Integer pages) {
        List<UserSendEntity> userSendEntities = service.selectaccept(send_uid, pages);
        return userSendEntities;
    }

    /**
     * selectaccountcount
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public Integer selectaccountcount(String send_uid) {
        int count = service.selectaccountcount(send_uid);
        return count;
    }

    /**
     * selectacceptcount
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public Integer selectacceptcount(String send_uid) {
        int count = service.selectacceptcount(send_uid);
        return count;
    }

}

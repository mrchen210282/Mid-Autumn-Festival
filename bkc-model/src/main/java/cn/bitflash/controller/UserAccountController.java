package cn.bitflash.controller;


import cn.bitflash.entity.UserAccountBean;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserAccountService;
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
public class UserAccountController {

    @Autowired
    private UserAccountService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */
    @PostMapping("/inner/userAccount/selectOne")
    public UserAccountEntity selectOne(Map<String, Object> param) {
        List<UserAccountEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserAccountEntity entity = entityList.get(0);
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
    @PostMapping("/inner/userAccount/selectById")
    public UserAccountEntity selectById(String id) {
        UserAccountEntity userAccountEntity = service.selectById(id);
        return userAccountEntity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userAccount/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(UserAccountEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserAccountEntity entity) {
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
     * updateUserAccountByParam
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateUserAccountByParam(UserAccountEntity userAccountEntity) {
        service.updateUserAccountByParam(userAccountEntity);
    }

    /**
     * updateUserAccountByParam
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public UserAccountBean selectUserAccount(Map<String, Object> map) {
        UserAccountBean userAccountBean = service.selectUserAccount(map);
        return userAccountBean;
    }
}

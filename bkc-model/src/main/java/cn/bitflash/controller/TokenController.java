package cn.bitflash.controller;


import cn.bitflash.entity.TokenEntity;
import cn.bitflash.entity.UserEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.TokenService;
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
public class TokenController {

    @Autowired
    private TokenService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public TokenEntity selectOne(Map<String, Object> param) {
        List<TokenEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            TokenEntity entity = entityList.get(0);
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
    public void updateById(TokenEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(TokenEntity entity) {
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
     * queryByToken
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public TokenEntity queryByToken(String mobile) {
        TokenEntity tokenEntity = service.queryByToken(mobile);
        return tokenEntity;
    }

    /**
     * createToken
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public TokenEntity createToken(UserEntity user) {
        TokenEntity tokenEntity = service.createToken(user);
        return tokenEntity;
    }

    /**
     * createToken
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void expireToken(String uid) {
        service.queryByToken(uid);
    }

}

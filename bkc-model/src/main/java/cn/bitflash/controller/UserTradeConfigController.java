package cn.bitflash.controller;


import cn.bitflash.entity.UserTradeConfigEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserTradeConfigService;
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
public class UserTradeConfigController {

    @Autowired
    private UserTradeConfigService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */
    @PostMapping("/inner/userTradeConfigEntity/selectOne")
    public UserTradeConfigEntity selectOne(Map<String, Object> param) {
        List<UserTradeConfigEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserTradeConfigEntity entity = entityList.get(0);
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
    public void updateById(UserTradeConfigEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserTradeConfigEntity entity) {
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

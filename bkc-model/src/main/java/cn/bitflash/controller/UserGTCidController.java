package cn.bitflash.controller;

import cn.bitflash.entity.UserGTCidEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserGTCidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserGTCidController {

    @Autowired
    private UserGTCidService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */
    @PostMapping("/inner/userGTCidEntity/selectOne")
    public UserGTCidEntity selectOne(Map<String, Object> param) {
        List<UserGTCidEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserGTCidEntity entity = entityList.get(0);
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
    public void updateById(UserGTCidEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserGTCidEntity entity) {
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

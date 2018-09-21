package cn.bitflash.controller;


import cn.bitflash.entity.UserPayUrlEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserPayUrlService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
public class UserPayUrlController {

    @Autowired
    private UserPayUrlService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserPayUrlEntity selectOne(Map<String, Object> param) {
        List<UserPayUrlEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserPayUrlEntity entity = entityList.get(0);
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
    public void updateById(UserPayUrlEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserPayUrlEntity entity) {
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

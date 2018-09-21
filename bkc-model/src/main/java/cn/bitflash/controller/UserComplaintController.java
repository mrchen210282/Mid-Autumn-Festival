package cn.bitflash.controller;


import cn.bitflash.entity.UserComplaintBean;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserComplaintService;
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
public class UserComplaintController {

    @Autowired
    private UserComplaintService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserComplaintEntity selectOne(Map<String, Object> param) {
        List<UserComplaintEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserComplaintEntity entity = entityList.get(0);
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
    public void updateById(UserComplaintEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userComplaint/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserComplaintEntity entity) {
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
     * getComplaintMessage
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public UserComplaintBean getComplaintMessage(String id) {
        UserComplaintBean userComplaintBean = service.getComplaintMessage(id);
        return userComplaintBean;
    }

}

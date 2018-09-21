package cn.bitflash.controller;


import cn.bitflash.entity.UserRelationEntity;
import cn.bitflash.entity.UserRelationJoinAccountEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserRelationService;
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
public class UserRelationController {

    @Autowired
    private UserRelationService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public UserRelationEntity selectOne(Map<String, Object> param) {
        List<UserRelationEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            UserRelationEntity entity = entityList.get(0);
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
    public void updateById(UserRelationEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(UserRelationEntity entity) {
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

    /**
     * insertTreeNode
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insertTreeNode(String f_uid, String c_uid, String invitation_code) {
        service.insertTreeNode(f_uid, c_uid, invitation_code);
    }

    /**
     * selectTreeNodes
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public List<UserRelationJoinAccountEntity> selectTreeNodes(String f_uid) {
        List<UserRelationJoinAccountEntity> entityList = service.selectTreeNodes(f_uid);
        return entityList;
    }

    /**
     * updateTreeNodes
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public int updateTreeNodes(Integer leftCode, String column) {
        int num = service.updateTreeNodes(leftCode, column);
        return num;
    }

    /**
     * selectLayer
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public int selectLayer(Integer rgt) {
        int num = service.selectLayer(rgt);
        return num;
    }

}

package cn.bitflash.controller;

import cn.bitflash.entity.AuthorityUserEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.AuthorityUserService;
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
public class AuthorityUserController {

    @Autowired
    private AuthorityUserService authorityUserService;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public AuthorityUserEntity selectOne(Map<String, Object> param) {
        List<AuthorityUserEntity> authorityUserEntities = authorityUserService.selectByMap(param);
        if (authorityUserEntities.size() > 0) {
            AuthorityUserEntity authorityUserEntity = authorityUserEntities.get(0);
            return authorityUserEntity;
        }
        return null;
    }

    /**
     * updateById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(AuthorityUserEntity authorityUserEntity) {
        authorityUserService.updateById(authorityUserEntity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(AuthorityUserEntity authorityUserEntity) {
        authorityUserService.insert(authorityUserEntity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(String id) {
        authorityUserService.deleteById(id);
    }

}

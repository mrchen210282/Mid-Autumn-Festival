package cn.bitflash.controller;


import cn.bitflash.entity.TokenEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.TokenService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/token/selectById")
    public TokenEntity selectById(@RequestParam("id") String id) {
        TokenEntity entity = tokenService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/token/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        TokenEntity entity = new TokenEntity();
        entity.setUid(json.getString("uid"));
        entity.setToken(json.getString("token"));
        entity.setExpireTime(json.getDate("expireTime"));
        entity.setUpdateTime(json.getDate("updateTime"));
        entity.setMobile(json.getString("mobile"));
        tokenService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/token/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        TokenEntity entity = new TokenEntity();
        entity.setUid(json.getString("uid"));
        entity.setToken(json.getString("token"));
        entity.setExpireTime(json.getDate("expireTime"));
        entity.setUpdateTime(json.getDate("updateTime"));
        entity.setMobile(json.getString("mobile"));
        tokenService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/token/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        tokenService.deleteById(id);
    }

    /**
     * insertOrUpdateToken
     */
    @PostMapping("/inner/token/insertOrUpdateToken")
    public Boolean insertOrUpdateToken(@RequestBody TokenEntity tokenEntity){
        return tokenService.insertOrUpdate(tokenEntity);
    }

}

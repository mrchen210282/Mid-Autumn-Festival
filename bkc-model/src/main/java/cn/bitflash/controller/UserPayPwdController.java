package cn.bitflash.controller;


import cn.bitflash.entity.UserPayPwdEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserPayPwdService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYUGUO
 */
@RestController
public class UserPayPwdController {

    @Autowired
    private UserPayPwdService userPayPwdService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userPayPwd/selectById")
    public UserPayPwdEntity selectById(@RequestParam("id") String id) {
        UserPayPwdEntity entity = userPayPwdService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userPayPwd/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserPayPwdEntity entity = new UserPayPwdEntity();
        entity.setUid(json.getString("uid"));
        entity.setPayPassword(json.getString("payPassword"));
        entity.setCreateTime(json.getDate("createTime"));
        userPayPwdService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userPayPwd/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserPayPwdEntity entity = new UserPayPwdEntity();
        entity.setUid(json.getString("uid"));
        entity.setPayPassword(json.getString("payPassword"));
        entity.setCreateTime(json.getDate("createTime"));
        userPayPwdService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userPayPwd/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        userPayPwdService.deleteById(id);
    }

}

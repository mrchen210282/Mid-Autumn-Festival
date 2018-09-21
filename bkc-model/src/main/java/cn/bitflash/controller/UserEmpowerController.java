package cn.bitflash.controller;


import cn.bitflash.entity.UserEmpowerEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserEmpowerService;
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
public class UserEmpowerController {

    @Autowired
    private UserEmpowerService userEmpowerService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/selectById")
    public UserEmpowerEntity selectById(@RequestParam("id") Integer id) {
        UserEmpowerEntity entity = userEmpowerService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserEmpowerEntity entity = new UserEmpowerEntity();
        entity.setId(json.getInteger("id"));
        entity.setAppid(json.getString("appid"));
        entity.setAppkey(json.getString("appkey"));
        entity.setTicket(json.getString("ticket"));
        entity.setCreateTime(json.getDate("createTime"));
        userEmpowerService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserEmpowerEntity entity = new UserEmpowerEntity();
        entity.setId(json.getInteger("id"));
        entity.setAppid(json.getString("appid"));
        entity.setAppkey(json.getString("appkey"));
        entity.setTicket(json.getString("ticket"));
        entity.setCreateTime(json.getDate("createTime"));
        userEmpowerService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") Integer id) {
        userEmpowerService.deleteById(id);
    }

}

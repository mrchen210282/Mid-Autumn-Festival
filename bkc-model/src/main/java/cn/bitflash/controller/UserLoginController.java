package cn.bitflash.controller;


import cn.bitflash.entity.UserLoginEntity;
import cn.bitflash.service.UserLoginService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userLogin/selectById")
    public UserLoginEntity selectById(@RequestParam("id") String id) {
        UserLoginEntity entity = userLoginService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userLogin/updateById")
    public void updateById(@RequestBody JSONObject json) throws Exception {
        UserLoginEntity entity = (UserLoginEntity) JSONObject.parseObject(json.toString(), UserLoginEntity.class);
        userLoginService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userLogin/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        UserLoginEntity entity = (UserLoginEntity) JSONObject.parseObject(json.toString(), UserLoginEntity.class);
        userLoginService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userLogin/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        userLoginService.deleteById(id);
    }

}

package cn.bitflash.controller;


import cn.bitflash.entity.UserLoginEntity;
import cn.bitflash.service.UserLoginService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserLoginEntity entity = (UserLoginEntity) JSONObject.parseObject(json.toString(), UserLoginEntity.class);
        return userLoginService.updateById(entity);
    }

    /**
     * updateByMobile
     *
     * @return
     */
    @PostMapping("/inner/userLogin/updateByMobile")
    public boolean updateByMobile(@RequestBody JSONObject json) throws Exception {
        UserLoginEntity entity = (UserLoginEntity) JSONObject.parseObject(json.toString(), UserLoginEntity.class);
        return userLoginService.update(entity,new EntityWrapper<UserLoginEntity>().eq("mobile",entity.getMobile()));
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userLogin/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserLoginEntity entity = (UserLoginEntity) JSONObject.parseObject(json.toString(), UserLoginEntity.class);
        return userLoginService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userLogin/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userLoginService.deleteById(id);
    }

    /**
     * selectByMobile
     *
     * @return
     */
    @PostMapping("/inner/userLogin/selectByMobile")
    public UserLoginEntity selectByMobile(@RequestParam("mobile") String mobile) {
        UserLoginEntity entity = userLoginService.selectOne(new EntityWrapper<UserLoginEntity>().eq("mobile",mobile));
        return entity;
    }

    @PostMapping("/inner/userLogin/registerLogin")
    public String registerLogin(@RequestBody Map<String,Object> map){
        return userLoginService.selectUid(map);
    }

    @PostMapping("updatepwd")
    public String updatepassword(){
        List<UserLoginEntity> list = userLoginService.selectList(new EntityWrapper<UserLoginEntity>());
        list.stream().forEach(u->{
            String salt = RandomStringUtils.randomAlphanumeric(16);
            u.setSalt(salt);
            u.setPassword(Encrypt.SHA256(u.getPassword()+salt));
        });
        userLoginService.updateBatchById(list);
        return "success";
    }

}

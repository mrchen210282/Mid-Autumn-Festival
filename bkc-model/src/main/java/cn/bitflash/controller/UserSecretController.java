package cn.bitflash.controller;


import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.service.UserSecretService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserSecretController {

    @Autowired
    private UserSecretService userSecretService;

    /**
     * selectById
     *
     * @returnss
     */
    @PostMapping("/inner/userLogin/selectById")
    public UserSecretEntity selectById(@RequestParam("id") String id) {
        UserSecretEntity entity = userSecretService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userLogin/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserSecretEntity entity = (UserSecretEntity) JSONObject.parseObject(json.toString(), UserSecretEntity.class);
        return userSecretService.updateById(entity);
    }

    /**
     * updateByMobile
     *
     * @return
     */
    @PostMapping("/inner/userLogin/updateByMobile")
    public boolean updateByMobile(@RequestBody JSONObject json) throws Exception {
        UserSecretEntity entity = (UserSecretEntity) JSONObject.parseObject(json.toString(), UserSecretEntity.class);
        return userSecretService.update(entity, new EntityWrapper<UserSecretEntity>().eq("mobile", entity.getMobile()));
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userLogin/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserSecretEntity entity = (UserSecretEntity) JSONObject.parseObject(json.toString(), UserSecretEntity.class);
        return userSecretService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userLogin/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userSecretService.deleteById(id);
    }

    /**
     * selectByMobile
     *
     * @return
     */
    @PostMapping("/inner/userLogin/selectByMobile")
    public UserSecretEntity selectByMobile(@RequestParam("mobile") String mobile) {
        UserSecretEntity entity = userSecretService.selectOne(new EntityWrapper<UserSecretEntity>().eq("mobile", mobile));
        return entity;
    }

    @PostMapping("/inner/userLogin/registerLogin")
    public String registerLogin(@RequestBody Map<String, Object> map) {
        return userSecretService.selectUid(map);
    }

    @PostMapping("updatepwd")
    public String updatepassword() {
        List<UserSecretEntity> list = userSecretService.selectList(new EntityWrapper<UserSecretEntity>());
        list.stream().forEach(u -> {
            String salt = RandomStringUtils.randomAlphanumeric(16);
            u.setSalt(salt);
            u.setPassword(Encrypt.SHA256(u.getPassword() + salt));
        });
        userSecretService.updateBatchById(list);
        return "success";
    }

}

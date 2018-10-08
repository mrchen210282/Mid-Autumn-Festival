package cn.bitflash.controller;


import cn.bitflash.entity.UserWalletAddressEntity;
import cn.bitflash.service.UserWalletAddressService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserWalletAddressController {

    @Autowired
    private UserWalletAddressService userWalletAddressService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userWalletAddress/selectById")
    public UserWalletAddressEntity selectById(@RequestParam("id") String id) {
        UserWalletAddressEntity entity = userWalletAddressService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userWalletAddress/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserWalletAddressEntity entity = (UserWalletAddressEntity) JSONObject.parseObject(json.toString(), UserWalletAddressEntity.class);
        return userWalletAddressService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userWalletAddress/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserWalletAddressEntity entity = (UserWalletAddressEntity) JSONObject.parseObject(json.toString(), UserWalletAddressEntity.class);
        return userWalletAddressService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userWalletAddress/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userWalletAddressService.deleteById(id);
    }

    /**
     * selectByAddress
     */
    @PostMapping("/inner/userWalletAddress/selectByAddress")
    public UserWalletAddressEntity selectByAddress(@RequestParam("address") String address){
        return userWalletAddressService.selectOne(new EntityWrapper<UserWalletAddressEntity>().eq("address",address));
    }
}

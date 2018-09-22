package cn.bitflash.controller;


import cn.bitflash.entity.UserWalletAddressEntity;
import cn.bitflash.service.UserWalletAddressService;
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
    public void updateById(@RequestBody JSONObject json) throws Exception {
        UserWalletAddressEntity entity = (UserWalletAddressEntity) JSONObject.parseObject(json.toString(), UserWalletAddressEntity.class);
        userWalletAddressService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userWalletAddress/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        UserWalletAddressEntity entity = (UserWalletAddressEntity) JSONObject.parseObject(json.toString(), UserWalletAddressEntity.class);
        userWalletAddressService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userWalletAddress/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        userWalletAddressService.deleteById(id);
    }

}

package cn.bitflash.controller;


import cn.bitflash.entity.UserPaymentCodeEntity;
import cn.bitflash.service.UserPaymentCodeService;
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
public class UserPaymentCodeController {

    @Autowired
    private UserPaymentCodeService userPaymentCodeService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userPaymentCode/selectById")
    public UserPaymentCodeEntity selectById(@RequestParam("id") String id) {
        UserPaymentCodeEntity entity = userPaymentCodeService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userPaymentCode/updateById")
    public void updateById(@RequestBody JSONObject json) throws Exception {
        UserPaymentCodeEntity entity = (UserPaymentCodeEntity) JSONObject.parseObject(json.toString(), UserPaymentCodeEntity.class);
        userPaymentCodeService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userPaymentCode/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        UserPaymentCodeEntity entity = (UserPaymentCodeEntity) JSONObject.parseObject(json.toString(), UserPaymentCodeEntity.class);
        userPaymentCodeService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userPaymentCode/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        userPaymentCodeService.deleteById(id);
    }

}

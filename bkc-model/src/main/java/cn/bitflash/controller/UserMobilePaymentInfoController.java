package cn.bitflash.controller;


import cn.bitflash.entity.UserMobilePaymentInfoEntity;
import cn.bitflash.service.UserMobilePaymentInfoService;
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
public class UserMobilePaymentInfoController {

    @Autowired
    private UserMobilePaymentInfoService userMobilePaymentInfoService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userMobilePaymentInfo/selectById")
    public UserMobilePaymentInfoEntity selectById(@RequestParam("id") String id) {
        UserMobilePaymentInfoEntity entity = userMobilePaymentInfoService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userMobilePaymentInfo/updateById")
    public void updateById(@RequestBody JSONObject json) throws Exception {
        UserMobilePaymentInfoEntity entity = (UserMobilePaymentInfoEntity) JSONObject.parseObject(json.toString(), UserMobilePaymentInfoEntity.class);
        userMobilePaymentInfoService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userMobilePaymentInfo/insert")
    public void insert(@RequestBody JSONObject json) throws Exception {
        UserMobilePaymentInfoEntity entity = (UserMobilePaymentInfoEntity) JSONObject.parseObject(json.toString(), UserMobilePaymentInfoEntity.class);
        userMobilePaymentInfoService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userMobilePaymentInfo/deleteById")
    public void deleteById(@RequestParam("id") String id) throws Exception {
        userMobilePaymentInfoService.deleteById(id);
    }

}

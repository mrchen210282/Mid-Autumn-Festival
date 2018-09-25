package cn.bitflash.controller;


import cn.bitflash.entity.UserBankPaymentInfoEntity;
import cn.bitflash.entity.UserMobilePaymentInfoEntity;
import cn.bitflash.service.UserBankPaymentInfoService;
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
public class UserBankPaymentInfoController {

    @Autowired
    private UserBankPaymentInfoService userBankPaymentInfoService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userBankPaymentInfo/selectById")
    public UserBankPaymentInfoEntity selectById(@RequestParam("id") String id) {
        UserBankPaymentInfoEntity entity = userBankPaymentInfoService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userBankPaymentInfo/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserBankPaymentInfoEntity entity = (UserBankPaymentInfoEntity) JSONObject.parseObject(json.toString(), UserBankPaymentInfoEntity.class);
        return userBankPaymentInfoService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userBankPaymentInfo/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserBankPaymentInfoEntity entity = (UserBankPaymentInfoEntity) JSONObject.parseObject(json.toString(), UserBankPaymentInfoEntity.class);
        return userBankPaymentInfoService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userBankPaymentInfo/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userBankPaymentInfoService.deleteById(id);
    }

    /**
     * insertOrUpdateBank
     */
    @PostMapping("/inner/userBankPaymentInfo/insertOrUpdateBank")
    public Boolean insertOrUpdateBank(@RequestBody UserBankPaymentInfoEntity bankInfo){
        return userBankPaymentInfoService.insertOrUpdate(bankInfo);
    }

}

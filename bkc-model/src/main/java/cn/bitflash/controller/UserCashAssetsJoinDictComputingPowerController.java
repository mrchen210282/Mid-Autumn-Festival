package cn.bitflash.controller;

import cn.bitflash.bean.UserCashAssetsJoinDictComputingPowerBean;
import cn.bitflash.service.UserCashAssetsJoinDictComputingPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangjun
 */
@RestController
public class UserCashAssetsJoinDictComputingPowerController {

    @Autowired
    private UserCashAssetsJoinDictComputingPowerService userCashAssetsJoinDictComputingPowerService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userCashAssetsJoinDictComputingPower/selectUserCashAssetsJoinDictComputingPower")
    public UserCashAssetsJoinDictComputingPowerBean selectUserCashAssetsJoinDictComputingPower(@RequestParam("uid") String uid) {
        return userCashAssetsJoinDictComputingPowerService.selectUserCashAssetsJoinDictComputingPower(uid);
    }
}

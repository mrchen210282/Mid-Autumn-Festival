package cn.bitflash.vip.user.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface UserFeign {

    /**
     * user_login表
     */
    @PostMapping("/inner/user/selectById")
    UserLoginEntity selectUserLoginByUid(@RequestParam("id") String id);

    @PostMapping("/inner/user/updateById")
    Boolean updateUserById(@RequestBody UserLoginEntity loginEntity);

    @PostMapping
    Boolean updateUserByMobile(@RequestBody UserLoginEntity loginEntity);


    /**
     * user_payment_code表
     */
    @PostMapping("")
    UserMobilePaymentCodeEntity selectPaymentByUidAndType(@RequestParam("uid")String uid, @RequestParam("imgType") String imgType);

    @PostMapping("")
    Boolean insertUserPayment(@RequestBody UserMobilePaymentCodeEntity payment);

    @PostMapping("")
    Boolean updateUserPaymentById(@RequestBody UserMobilePaymentCodeEntity payment);

    @PostMapping
    List<UserMobilePaymentCodeEntity>  selectPaymentsByUid(@RequestParam("id")String id);


    /**
     * system_resource
     */
    @PostMapping("")
    SystemResourceEntity selectSysResourceById(@RequestParam("id")int id);

    /**
     * user_wallet_address
     */
    @PostMapping("")
    void insetUserWalletAddress(@RequestBody UserWalletAddressEntity userWalletAddress);

    /**
     * user_info 表
     */
    @PostMapping
    UserInfoEntity selectUserinfoById(@RequestParam("id")String id);

    @PostMapping
    Boolean updateUserInfoById(@RequestBody UserInfoEntity info);

}

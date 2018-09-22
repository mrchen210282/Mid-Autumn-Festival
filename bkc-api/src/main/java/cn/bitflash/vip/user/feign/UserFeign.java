package cn.bitflash.vip.user.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bkc-model")
public interface UserFeign {

    /**
     * tb_user表
     */
    @PostMapping("/inner/user/selectById")
    UseLoginEntity selectUserLoginByUid(@RequestParam("id") String id);

    @PostMapping("/inner/user/updateById")
    Boolean updateUserById(@RequestBody UseLoginEntity userEntity);


    /**
     * user_payment_code表
     */
    @PostMapping("")
    UserPaymentCodeEntity selectPaymentByUidAndType(@RequestParam("uid")String uid,@RequestParam("imgType") String imgType);

    @PostMapping("")
    Boolean insertUserPayment(@RequestBody UserPaymentCodeEntity payment);

    @PostMapping("")
    Boolean updateUserPaymentById(@RequestBody UserPaymentCodeEntity payment);

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

}

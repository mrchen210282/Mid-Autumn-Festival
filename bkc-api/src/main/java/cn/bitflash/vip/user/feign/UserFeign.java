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
    @PostMapping("/inner/userLogin/selectById")
    UserLoginEntity selectUserLoginByUid(@RequestParam("id") String id);

    @PostMapping("/inner/userLogin/updateById")
    Boolean updateUserById(@RequestBody UserLoginEntity loginEntity);

    @PostMapping("/inner/userLogin/updateById")
    Boolean updateUserByMobile(@RequestBody UserLoginEntity loginEntity);


    /**
     * user_payment_code表
     */
    @PostMapping("/inner/userMobilePaymentInfo/selectPaymentByUidAndType")
    UserMobilePaymentInfoEntity selectPaymentByUidAndType(@RequestParam("uid")String uid, @RequestParam("type") String type);

    @PostMapping("/inner/userMobilePaymentInfo/insert")
    void insertUserPayment(@RequestBody UserMobilePaymentInfoEntity payment);

    @PostMapping("/inner/userMobilePaymentInfo/updateById")
    void updateUserPaymentById(@RequestBody UserMobilePaymentInfoEntity payment);

    @PostMapping("/inner/userMobilePaymentInfo/selectPaymentsByUid")
    List<UserMobilePaymentInfoEntity>  selectPaymentsByUid(@RequestParam("id")String id);

    /**
     * user_wallet_address
     */
    @PostMapping("/inner/userWalletAddress/insert")
    void insetUserWalletAddress(@RequestBody UserWalletAddressEntity userWalletAddress);

    /**
     * user_info 表
     */
    @PostMapping("/inner/userInfo/selectById")
    UserInfoEntity selectUserinfoById(@RequestParam("id")String id);

    @PostMapping("/inner/userInfo/updateById")
    void updateUserInfoById(@RequestBody UserInfoEntity info);

    /**
     * user_bank_payment_info 表
     */
    @PostMapping("/inner/userBankPaymentInfo/insertOrUpdateBank")
    Boolean insertOrUpdateBank(@RequestBody UserBankPaymentInfoEntity bankInfo);

}

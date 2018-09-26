package cn.bitflash.vip.user.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
    Boolean insertUserPayment(@RequestBody UserMobilePaymentInfoEntity payment);

    @PostMapping("/inner/userMobilePaymentInfo/updateById")
    Boolean updateUserPaymentById(@RequestBody UserMobilePaymentInfoEntity payment);

    @PostMapping("/inner/userMobilePaymentInfo/selectPaymentsByUid")
    List<UserMobilePaymentInfoEntity>  selectPaymentsByUid(@RequestParam("id")String id);

    /**
     * user_wallet_address
     */
    @PostMapping("/inner/userWalletAddress/insert")
    Boolean insetUserWalletAddress(@RequestBody UserWalletAddressEntity userWalletAddress);

    @PostMapping("/inner/userWalletAddress/selectById")
    UserWalletAddressEntity selectUserWalletAddressById(@RequestParam("id")String id);

    /**
     * user_info 表
     */
    @PostMapping("/inner/userInfo/selectById")
    UserInfoEntity selectUserinfoById(@RequestParam("id")String id);

    @PostMapping("/inner/userInfo/updateById")
    Boolean updateUserInfoById(@RequestBody UserInfoEntity info);

    /**
     * user_bank_payment_info 表
     */
    @PostMapping("/inner/userBankPaymentInfo/insertOrUpdateBank")
    Boolean insertOrUpdateBank(@RequestBody UserBankPaymentInfoEntity bankInfo);

    @PostMapping("/inner/userCashAssetsJoinDictComputingPower/selectUserCashAssetsJoinDictComputingPower")
    UserCashAssetsJoinDictComputingPowerBean selectUserCashAssetsJoinDictComputingPower(@RequestParam("uid") String uid);

    @PostMapping("/inner/userPerformance/selectById")
    UserPerformanceEntity selectUserPerformanceById(@RequestParam("id") String id);

    @PostMapping("/inner/userDigitalAssets/selectById")
    UserDigitalAssetsEntity selectDigitalAssetsById(@RequestParam("id") String id);
    @PostMapping("/inner/userBankPaymentInfo/selectById")
    UserBankPaymentInfoEntity selectBankInfoByUid(@RequestParam("id")String id);

    /**
     * user_cash_assets 表
     */
    @PostMapping("/inner/userCashAssets/selectById")
    UserCashAssetsEntity selectCashAssetsByUid(@RequestParam("id")String id);

    @PostMapping("/inner/userCashAssets/updateById")
    Boolean updateCashAssetsByUid(@RequestBody UserCashAssetsEntity cashAssets);

    /**
     * user_drawing 表
     */
    @PostMapping("")
    Boolean insertDrawingInfo(@RequestBody UserDrawingEntity drawingInfo);





}

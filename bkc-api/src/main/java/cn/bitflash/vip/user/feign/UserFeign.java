package cn.bitflash.vip.user.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@FeignClient(value = "bkc-model")
public interface UserFeign {

    /**
     * user_secret 表
     */
    @PostMapping("/inner/userLogin/selectById")
    UserSecretEntity selectUserLoginByUid(@RequestParam("id") String id);

    @PostMapping("/inner/userLogin/updateById")
    Boolean updateUserById(@RequestBody UserSecretEntity loginEntity);

    @PostMapping("/inner/userLogin/updateByMobile")
    Boolean updateUserByMobile(@RequestBody UserSecretEntity loginEntity);


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
    List<UserMobilePaymentInfoEntity>  selectPaymentsByUid(@RequestParam("uid")String uid);

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

    @PostMapping("/inner/userBankPaymentInfo/selectById")
    UserBankPaymentInfoEntity selectBankInfoByUid(@RequestParam("id")String id);

    /**
     * system_resource 表
     */
    @PostMapping("/inner/systemResource/getPath")
    String getPath(@RequestParam("id")Integer id);

    /**
     * system_param 表
     */
    @PostMapping("/inner/systemParam/getVal")
    String getVal(@RequestParam("key") String key);

    /**
     * daily_total_npc 表
     */
    @PostMapping("/inner/dailytotalnpc/selectById")
    DailyTotalNpcEntity selectDailyTotalNpcEntityById(@RequestParam("id")LocalDate id);

    @PostMapping("/inner/dailytotalnpc/update")
    Boolean updateDailyTotalNpc(@RequestBody DailyTotalNpcEntity daily);


    /**
     * user_npc_trade_hostory 表
     */
    @PostMapping("/inner/usernpchistory/insert")
    Boolean insertUserNpcEntity(@RequestBody UserNpcTradeHistoryEntity npcEntity);

    @PostMapping("/inner/usernpchistory/selectNpchistory")
    List<UserNpcTradeHistoryEntity> selectNpchistory(@RequestParam("uid")String uid);

    /**
     * user_assets_hlb 表
     */
    @PostMapping("/inner/userAssetsHlb/selectById")
    UserAssetsHlbEntity selectUserAssetsHlbById(@RequestParam("id")String id);

    @PostMapping("/inner/userAssetsHlb/update")
    Boolean updateUserAssetsHlb(@RequestBody UserAssetsHlbEntity hlbEntity);

    /**
     * user_assets_npc 表
     */
    @PostMapping("/inner/userAssetsNpc/selectById")
    UserAssetsNpcEntity selectUserAssetsNpcById(@RequestParam("id")String id);

    @PostMapping("/inner/userAssetsNpc/updateUserAssetsNpc")
    Boolean updateUserAssetsNpc(@RequestBody UserAssetsNpcEntity npcEntity);

    /**
     * 首页资产
     */
//    @PostMapping("/inner/userCashAssetsJoinDictComputingPower/selectUserCashAssetsJoinDictComputingPower")
//    UserCashAssetsJoinDictComputingPowerBean selectUserCashAssetsJoinDictComputingPower(@RequestParam("uid") String uid);
//
//    @PostMapping("/inner/userDigitalAssets/selectById")
//    UserDigitalAssetsEntity selectDigitalAssetsById(@RequestParam("id") String id);



}

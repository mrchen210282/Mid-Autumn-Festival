package cn.bitflash.vip.usersend.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface SendFrign {


//    /**
//     * tb_user表
//     */
//    @PostMapping("/inner/user/selectByUuid")
//    UserEntity selectUserByUuid(@RequestParam("uuid") String uuid);
//
//    /**
//     * user_pay_pwd 表
//     */
//    @PostMapping("/inner/userPayPwd/selectById")
//    UserPayPwdEntity selectUserPwdByUid(@RequestParam("id") String uid);
//
//    /**
//     * user_trade_config 表
//     */
//    @PostMapping("/inner/userTradeConfig/selectById")
//    UserTradeConfigEntity selectTradeConfigById(@RequestParam("id") int id);
//
//    /**
//     * user_brokerage表
//     */
//    @PostMapping("/inner/userBrokerage/selectById")
//    UserBrokerageEntity selectUserBrokerage(@RequestParam("id") int id);
//
//    @PostMapping("/inner/userBrokerage/updateById")
//    Boolean updateUserBrokerageById(@RequestBody UserBrokerageEntity brokerage);
//
//
//    /**
//     * user_account表
//     */
//    @PostMapping("/inner/userAccount/selectById")
//    UserAccountEntity selectAccountByUid(@RequestParam("id") String uid);
//
//    @PostMapping("/inner/userAccount/updateById")
//    Boolean updateAccountById(@RequestBody UserAccountEntity account);
//
//    /**
//     * user_send 表
//     */
//    @PostMapping("/inner/userSend/insert")
//    Boolean insertUserSend(@RequestBody UserSendEntity send);
//
//    @PostMapping("/inner/userSend/selectAccount")
//    List<UserSendEntity> selectAccountByPages(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);
//
//    @PostMapping("/inner/userSend/selectaccept")
//    List<UserSendEntity> selectaccept(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);
//
//    @PostMapping("/inner/userSend/selectaccountcount")
//    Integer selectaccountcount(@RequestParam("uid") String uid);

    /**
     * user_wallet_address
     */
    @PostMapping("")
    UserWalletAddressEntity selectAddress(@RequestParam("address") String address);

    /**
     * user_secret
     */
    @PostMapping("")
    UserSecretEntity selectSecretById(@RequestParam("id") String id);

    /**
     * user_market_config
     */
    @PostMapping("")
    UserMarketConfigEntity selectConfigById(@RequestParam("id") int id);

    /**
     * user_assets_npc
     */
    @PostMapping("")
    UserAssetsNpcEntity selectAssetsById(@RequestParam("id") String id);

    @PostMapping("")
    UserAssetsNpcEntity updateAssetsById(@RequestBody UserAssetsNpcEntity userAssetsNpcEntity);

    /**
    * user_send 表
    */
    @PostMapping("/inner/userSend/insert")
    Boolean insertUserSend(@RequestBody UserSendEntity send);

    /**
     * UserBrokerageEntity 表
     */
    UserBrokerageEntity selectBrokerage(@RequestParam("id") int id);

    Boolean updateBrokerage(@RequestBody UserBrokerageEntity userBrokerageEntity);

    int selectSendcount(@RequestParam("uid") String uid);

    List<UserSendEntity> selectSendList(@RequestParam("uid") String uid,@RequestParam("pages") int pages);

}

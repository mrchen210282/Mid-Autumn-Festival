package cn.bitflash.vip.usersend.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface SendFrign {

    /**
     * user_send 表
     */
    @PostMapping("/inner/userSend/insert")
    Boolean insertUserSend(@RequestBody UserSendEntity send);

    @PostMapping("/inner/userSend/selectAccount")
    List<UserSendEntity> selectAccount(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages);

    @PostMapping("/inner/userSend/selectAccept")
    List<UserSendEntity> selectAccept(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages);

    @PostMapping("/inner/userSend/selectAccountCount")
    Integer selectAccountCount(@RequestParam("uid")String uid);

    @PostMapping("/inner/userSend/selectAcceptCount")
    Integer selectAcceptCount(@RequestParam("uid")String send_uid);

    /**
     * user_wallet_address
     */
    @PostMapping("/inner/userWalletAddress/selectByAddress")
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
     * UserBrokerageEntity 表
     */
    UserBrokerageEntity selectBrokerage(@RequestParam("id") int id);

    Boolean updateBrokerage(@RequestBody UserBrokerageEntity userBrokerageEntity);

}

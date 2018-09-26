package cn.bitflash.vip.level.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface LevelFeign {


    /**
     * user_info 表
     */
    @PostMapping("/inner/userInfo/selectById")
    UserInfoEntity selectUserInfoByUid(@RequestParam("id") String uid);

    /**
     * user_relation 表
     */
    @PostMapping("/inner/userRelation/selectRelationByCode")
    UserRelationEntity selectRelationByUid(@RequestParam("id") String id);

    @PostMapping("")
    List<UserRelationEntity> selectTreeNodes(@RequestParam("uid") String uid);

    /**
     * user_invitation_code 表
     */
    @PostMapping("/inner/userInvitationCode/selectById")
    UserInvitationCodeEntity selectInvitationCodeByUid(@RequestParam("id") String uid);

    @PostMapping("/inner/userInvitationCode/selectCodeByCode")
    UserInvitationCodeEntity selectInvitationCodeByCode(@RequestParam("code") String code);

    @PostMapping("/inner/userRelation/insertTreeNode")
    Boolean insertTreeNode(@RequestParam("pid") String pid, @RequestParam("uid") String uid, @RequestParam("code") String code);


    /**
     * user_cash_Assets 表
     */
    @PostMapping("")
    UserCashAssetsEntity selectCashAssetsByUid(@RequestParam("id")String id);

    @PostMapping("")
    Boolean updateUserCashAssetsById(@RequestBody UserCashAssetsEntity cash);

    /**
     * user_performance 表
     */
    @PostMapping("")
    UserPerformanceEntity selectPerformanceByUid(@RequestParam("id")String id);

    /**
     * system_resource 表
     */
    @PostMapping("")
    String getPath(@RequestParam("id")Integer id);

    /**
     * dict_computer_power 表
     */
    @PostMapping()
    List<DictComputingPowerEntity>selectComputerPowersById(@RequestParam("level1")Integer level1,@RequestParam("level2")Integer level2);


    /**
     * user_digital_assets 表
     */
    @PostMapping()
    UserDigitalAssetsEntity selectDigitalAssetsByUid(@RequestParam("id")String id);

    @PostMapping()
    Boolean updateDigitalAssetsByUid(@RequestBody UserDigitalAssetsEntity dig);

}

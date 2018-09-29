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
    @PostMapping("/inner/userRelation/selectById")
    UserRelationEntity selectRelationByUid(@RequestParam("id") String id);

    @PostMapping("/inner/userRelation/selectTreeNodes")
    List<UserRelationEntity> selectTreeNodes(@RequestParam("uid") String uid);

    /**
     * user_invitation_code 表
     */
    @PostMapping("/inner/userInvitationCode/selectById")
    UserInvitationCodeEntity selectInvitationCodeByUid(@RequestParam("id") String uid);

    @PostMapping("/inner/userInvitationCode/selectInvitationCodeByCode")
    UserInvitationCodeEntity selectInvitationCodeByCode(@RequestParam("code") String code);

    @PostMapping("/inner/userRelation/insertTreeNode")
    Boolean insertTreeNode(@RequestParam("pid") String pid, @RequestParam("uid") String uid,
                           @RequestParam("code") String code,@RequestParam("area")String area);


    /**
     * user_cash_Assets 表
     */
    @PostMapping("/inner/userCashAssets/selectById")
    UserCashAssetsEntity selectCashAssetsByUid(@RequestParam("id")String id);

    @PostMapping("/inner/userCashAssets/updateById")
    Boolean updateUserCashAssetsById(@RequestBody UserCashAssetsEntity cash);

    /**
     * user_performance 表
     */
    @PostMapping("/inner/userPerformance/selectById")
    UserPerformanceEntity selectPerformanceByUid(@RequestParam("id")String id);

    /**
     * system_resource 表
     */
    @PostMapping("/inner/systemResource/getPath")
    String getPath(@RequestParam("id")Integer id);

    /**
     * dict_computer_power 表
     */
    @PostMapping("/inner/dictComputingPower/selectComputerPowersById")
    List<DictComputingPowerEntity> selectComputerPowersById(@RequestParam("level1")Integer level1,@RequestParam("level2")Integer level2);


    /**
     * user_digital_assets 表
     */
    @PostMapping("/inner/userDigitalAssets/selectById")
    UserDigitalAssetsEntity selectDigitalAssetsByUid(@RequestParam("id")String id);

    @PostMapping("/inner/userDigitalAssets/updateById")
    Boolean updateDigitalAssetsByUid(@RequestBody UserDigitalAssetsEntity dig);

}

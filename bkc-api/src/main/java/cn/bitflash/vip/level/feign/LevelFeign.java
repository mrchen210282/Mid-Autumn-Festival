package cn.bitflash.vip.level.feign;

import cn.bitflash.entity.*;
import cn.bitflash.vip.level.entity.UserRelationJoinAccountEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface LevelFeign {

    /**
     * user_account 表
     */
    @PostMapping("/inner/userAccount/selectById")
    UserDigitalIncomeEntity selectAccountByUid(@RequestParam("id") String uid);

    @PostMapping("/inner/userAccount/updateById")
    Boolean updateAccountById(@RequestBody UserDigitalIncomeEntity account);

    /**
     * user_info 表
     */
    @PostMapping("/inner/userInfo/selectById")
    UserInfoEntity selectUserInfoByUid(@RequestParam("id") String uid);

    /**
     * user_relation 表
     */
    @PostMapping("/inner/userRelation/selectRelationByCode")
    UserRelationEntity selectRelationByCode(@RequestParam("code") String code);

    @PostMapping("/inner/userRelation/selectTreeNodes")
    List<UserRelationJoinAccountEntity> selectTreeNodes(@RequestParam("uid") String uid);

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
     * platform_config 表
     */
    @PostMapping("/inner/platFormConfig/getVal")
    String getVal(@RequestParam("key") String key);

    /**
     * vip_conditions 表
     */
    @PostMapping("/inner/VipConditions/selectVipConditonsByLevel")
    List<VipConditionsEntity> selectVipConditonsByLevel(@RequestParam("level") String level);

    /**
     * user_cash_income 表
     */
    @PostMapping("/inner/userCashIncome/selectById")
    UserCashIncomeEntity selectUserCashIncomeByUid(@RequestParam("id") String uid);

    @PostMapping("/inner/userCashIncome/updateById")
    void updateUserCashIncomeById(@RequestBody UserCashIncomeEntity cashIncome);
}

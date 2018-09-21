package cn.bitflash.vip.level.feign;

import cn.bitflash.entity.*;
import cn.bitflash.vip.level.entity.UserRelationJoinAccountEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface LevelFeign {

    /**
     * user_account 表
     */
    UserAccountEntity selectAccountByUid(@RequestParam("uid")String uid);

    Boolean updateAccountById(@RequestBody UserAccountEntity account);

    /**
     * user_info 表
     */
    UserInfoEntity selectUserInfoByUid(@RequestParam("uid")String uid);

    Boolean updateUserInfoById(@RequestBody UserInfoEntity userInfo);

    /**
     * user_relation 表
     */
    UserRelationEntity selectRelationByCode(@RequestParam("code")String code);

    List<UserRelationJoinAccountEntity>  selectTreeNodes(@RequestParam("uid")String uid);

    /**
     * user_invitation_code 表
     */
    UserInvitationCodeEntity selectInvitationCodeByUid(@RequestParam("uid")String uid);

    UserInvitationCodeEntity selectInvitationCodeByCode(@RequestParam("code")String code);

    Boolean insertInvitationCode(@RequestBody UserInvitationCodeEntity code);

    Boolean insertTreeNode(@RequestParam("pid")String pid,@RequestParam("uid") String uid,@RequestParam("code")String code);

    /**
     * platform_config 表
     */
    String getVal(@RequestParam("key")String key);

    /**
     * user_info_config表
     */
    UserInfoConfigEntity selectInfoConfigById(@RequestParam("vip")Integer vip);
}

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
     * user_secret
     */
    @PostMapping("/inner/userLogin/selectById")
    UserSecretEntity selectUserSecretById(@RequestParam("id") String id);
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

    @PostMapping("/inner/userRelation/insertTreeNode")
    Boolean insertTreeNode(@RequestParam("pid") String pid, @RequestParam("uid") String uid,
                           @RequestParam("code") String code,@RequestParam("area")String area);

    /**
     * user_invitation_code 表
     */
    @PostMapping("/inner/userInvitationCode/selectById")
    UserInvitationCodeEntity selectInvitationCodeByUid(@RequestParam("id") String uid);

    @PostMapping("/inner/userInvitationCode/selectInvitationCodeByCode")
    UserInvitationCodeEntity selectInvitationCodeByCode(@RequestParam("code") String code);

    @PostMapping("/inner/userInvitationCode/insert")
    Boolean insertInvitation(@RequestBody UserInvitationCodeEntity code);

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

    @PostMapping
    Boolean updateUserAssetsNpc(@RequestBody UserAssetsNpcEntity npcEntity);

    /**
     * system_param 表
     */
    @PostMapping
    String getVal(@RequestParam("key") String key);


}

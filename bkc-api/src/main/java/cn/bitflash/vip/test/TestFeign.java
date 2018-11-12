//package cn.bitflash.vip.test;
//
//import cn.bitflash.entity.UserInfoEntity;
//import cn.bitflash.entity.UserInvitationCodeEntity;
//import cn.bitflash.entity.UserRelationEntity;
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@FeignClient(value = "bkc-model")
//public interface TestFeign {
//
//    @PostMapping("/inner/userRelation/selectUserInvitationCode")
//    public List<UserInvitationCodeEntity> selectUserInvitationCode(@RequestParam("code") String code);
//
//    @PostMapping("/inner/userRelation/selectUserRelation")
//    public UserRelationEntity selectUserRelation(@RequestParam("uid") String uid);
//
//    @PostMapping("/inner/userRelation/selectUserRelationCode")
//    public List<UserRelationEntity> selectUserRelationCode(@RequestParam("code") String code);
//
//    @PostMapping("/inner/userInfo/selectById")
//    public UserInfoEntity selectById(@RequestParam("id") String id);
//
//    @PostMapping("inner/userInfo/selectUserInfoByOldUid")
//    public UserInfoEntity selectUserInfoByOldUid(@RequestParam("uid")String uid);
//
//
//    /**
//     * updateById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userInfo/updateById")
//    public boolean updateById(@RequestBody UserInfoEntity userInfoEntity);
//
//    /**
//     * updateById
//     *
//     * @return
//     */
//    @PostMapping("/inner/userRelation/updateById")
//    public boolean updateUserRelationById(@RequestBody UserRelationEntity userRelationEntity);
//
//
//    /**
//     * insert
//     *
//     * @return
//     */
//    @PostMapping("/inner/userInvitationCode/insert")
//    public boolean insert(@RequestBody UserInvitationCodeEntity userInvitationCodeEntity);
//}

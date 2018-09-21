package cn.bitflash.vip.index.feign;

import cn.bitflash.entity.TokenEntity;
import cn.bitflash.entity.UserEntity;
import cn.bitflash.entity.UserGTCidEntity;
import cn.bitflash.entity.UserInvitationCodeEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Api(value = "初始访问数据接口")
@FeignClient(value = "bkc-model")
public interface IndexFeign {

    /**
     * tb_user表
     */
    @ApiOperation(value = "根据手机号查询用户是否存在")
    UserEntity selectUserEntityByMobile(@RequestParam("mobile")String mobile);

    @ApiOperation(value = "根据手机号删除用户信息")
    void delUserEntityBymMbile(@RequestParam("mobile")String mobile);

    @ApiOperation(value = "插入tb_user表")
    Boolean insertUserEntity(@RequestParam("uid")String uid,@RequestParam("mobile")String mobile,
                             @RequestParam("password")String password,@RequestParam("uuid")String uuid);

    /**
     * tb_token表
     */
    @ApiOperation(value = "插入或者更新用户token值")
    Boolean insertOrUpdateToken(@RequestBody TokenEntity tokenEntity);

    /**
     * user_getui_cid表
     */
    @ApiOperation(value = "插入或者更新用户cid值")
    Boolean insertOrUpdateGT(@RequestBody UserGTCidEntity userGTCidEntity);

    /**
     * user_account表
     */
    @ApiOperation(value = "插入user_account表")
    Boolean insertAccount(@RequestParam("uid")String uid, @RequestParam("date")Date date);

    @ApiOperation(value = "根据uid删除user_account表")
    void delAccountByUid(@RequestParam("uid")String uid);

    /**
     * user_account_game表
     */
    @ApiOperation(value = "插入user_account_game表")
    Boolean insertGame(@RequestParam("uid")String uid, @RequestParam("date")Date date);

    @ApiOperation(value = "根据uid删除user_account_game表")
    void delGameByUid(@RequestParam("uid")String uid);

    /**
     * user_info表
     */
    @ApiOperation(value = "插入user_info表")
    Boolean insertInfo(@RequestParam("uid")String uid,@RequestParam("mobile")String mobile,
                       @RequestParam("flag")boolean flag,@RequestParam("name")String name);

    @ApiOperation(value = "根据uid删除user_info表")
    void delUserInfoByUid(@RequestParam("uid")String uid);

    Boolean insertInfoCode(@RequestParam("uid")String uid,@RequestParam("mobile")String mobile,
                           @RequestParam("flag")boolean flag,@RequestParam("name")String name
            ,@RequestParam("code")String code);

    /**
     * user_invitation_code 表
     */
    UserInvitationCodeEntity selectCodeByCode(@RequestParam("code")String code);
}

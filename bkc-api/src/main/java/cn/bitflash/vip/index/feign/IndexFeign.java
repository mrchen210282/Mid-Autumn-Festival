package cn.bitflash.vip.index.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bkc-model")
public interface IndexFeign {

    /**
     * user_login表
     */
    //根据手机号查询用户
    @PostMapping("")
    UserLoginEntity selectUserLoginEntityByMobile(@RequestParam("id")String mobile);

    //插入
    @PostMapping("")
    String insertUserLoginEntity(@RequestBody UserLoginEntity userEntity);

    //更新
    @PostMapping("")
    Boolean updateUserLoginById(@RequestBody UserLoginEntity userEntity);

    /**
     * tb_token表
     */
    //插入或者更新用户token值
    @PostMapping("/inner/token/insertOrUpdateToken")
    Boolean insertOrUpdateToken(@RequestBody UserTokenEntity tokenEntity);

    /**
     * system_uid
     */
    //获取主键uid
    @PostMapping("")
    String selectUid();

    /**
     * user_info表
     */
    //更新
    @PostMapping()
    Boolean updateUserInfoById(@RequestBody UserInfoEntity infoEntity);
}

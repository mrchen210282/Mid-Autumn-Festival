package cn.bitflash.vip.index.feign;

import cn.bitflash.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/inner/user/selectById")
    UseLoginEntity selectUserLoginEntityByMobile(@RequestParam("id")String mobile);

    @ApiOperation(value = "根据手机号删除用户信息")
    @PostMapping("/inner/user/deleteById")
    void delUserEntityByMbile(@RequestParam("id")String mobile);

    @ApiOperation(value = "插入tb_user表")
    @PostMapping("/inner/user/insert")
    Boolean insertUserLoginEntity(@RequestBody UseLoginEntity userEntity);

    /**
     * tb_token表
     */
    @ApiOperation(value = "插入或者更新用户token值")
    @PostMapping("/inner/token/insertOrUpdateToken")
    Boolean insertOrUpdateToken(@RequestBody UserTokenEntity tokenEntity);

    /**
     * system_uid
     */
    String selectUid();
}

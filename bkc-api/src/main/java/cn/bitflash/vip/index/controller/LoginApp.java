package cn.bitflash.vip.index.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UseLoginEntity;
import cn.bitflash.entity.UserTokenEntity;
import cn.bitflash.interceptor.ApiLoginInterceptor;
import cn.bitflash.utils.*;
import cn.bitflash.vip.index.entity.LoginForm;
import cn.bitflash.vip.index.feign.IndexFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/index")
@Api(value = "登录Con", tags = {"用户app登录"})
public class LoginApp {

    @Autowired
    private IndexFeign indexFeign;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("login")
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "mobile", dataType = "String"),
            @ApiImplicitParam(value = "password", dataType = "password"),
    })
    public R login(@RequestBody LoginForm form) {
        // 表单校验
        ValidatorUtils.validateEntity(form);
        UseLoginEntity user = indexFeign.selectUserEntityByMobile(form.getMobile());

        // 密码错误
        if (user == null || !user.getPassword().equals(form.getPassword())) {
            return R.error("手机号或密码错误");
        }
        // 插入token
        String token = generateToken();
        UserTokenEntity tokenEntity = new UserTokenEntity();
        tokenEntity.setUid(user.getUid());
        tokenEntity.setToken(token);
        tokenEntity.setUpdateTime(new Date());
        indexFeign.insertOrUpdateToken(tokenEntity);
        //缓存key
        String tokenKey = RedisDetail.REDIS_TOKEN + user.getUid();
        //加入缓存，失效时间15天
        redisUtils.set(tokenKey, tokenEntity, RedisDetail.REDIS_TOKEN_EXPIRED_TIME);
        Map<String, Object> map = new HashMap<>(2);
        String time = System.currentTimeMillis() + "bkc";
        map.put("token", AESTokenUtil.setToken(time, tokenEntity.getToken()));
        map.put("expire", time);
        return R.ok(map);
    }

    @Login
    @PostMapping("logout")
    public R logout(@RequestAttribute(ApiLoginInterceptor.UID) String uid) {
        String tokenKey = RedisDetail.REDIS_TOKEN + uid;
        redisUtils.delete(tokenKey);
        return R.ok();
    }

    private String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

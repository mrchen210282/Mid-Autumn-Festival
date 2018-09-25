package cn.bitflash.vip.index.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserLoginEntity;
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

import java.util.HashMap;
import java.util.Map;


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
        UserLoginEntity user = indexFeign.selectUserLoginEntityByMobile(form.getMobile());
        if (user == null) {
            return R.error("用户不存在");
        }
        String finalPwd = Encrypt.SHA512(form.getPassword() + user.getSalt());
        // 密码错误
        if (user.getPassword().equals(form.getPassword()) || user.getPassword().equals(finalPwd)) {
            // 插入token
            String token = Encrypt.SHA512(System.currentTimeMillis() + form.getMobile());
            user.setToken(token);
            indexFeign.updateUserLoginById(user);
            //缓存key
            String tokenKey = RedisDetail.REDIS_TOKEN + user.getUid();
            //加入缓存，失效时间15天
            redisUtils.set(tokenKey, user, RedisDetail.REDIS_TOKEN_EXPIRED_TIME);
            Map<String, Object> map = new HashMap<String, Object>(2);
            String time = System.currentTimeMillis() + "bkc";
            map.put("token", AESTokenUtil.setToken(time, user.getToken()));
            map.put("expire", time);
            return R.ok(map);

        }
        return R.error("手机号或密码错误");
    }

    @Login
    @PostMapping("logout")
    public R logout(@RequestAttribute(ApiLoginInterceptor.UID) String uid) {
        String tokenKey = RedisDetail.REDIS_TOKEN + uid;
        redisUtils.delete(tokenKey);
        return R.ok();
    }
}

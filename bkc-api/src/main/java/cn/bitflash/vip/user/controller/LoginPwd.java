package cn.bitflash.vip.user.controller;


import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UseLoginEntity;
import cn.bitflash.interceptor.ApiLoginInterceptor;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(value = "登录密码操作Con", tags = {"修改，找回"})
public class LoginPwd {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("updateLoginPwd")
    @ApiOperation("修改登录密码")
    public R updateLoginPwd(@RequestAttribute(ApiLoginInterceptor.UID) String uid, @ApiParam @RequestParam String oldPwd,@ApiParam @RequestParam String newPwd) {
        UseLoginEntity user = userFeign.selectUserByUid(uid);
        if (oldPwd.equals(user.getPassword())) {
            user.setPassword(newPwd);
            userFeign.updateUserById(user);
            return R.ok();
        } else {
            return R.error("原密码不正确");
        }
    }

    @PostMapping("findLoginPwd")
    @ApiOperation("找回登录密码")
    public R findLoginPwd(@ApiParam @RequestParam String mobile, @ApiParam @RequestParam String newPwd) {
        UseLoginEntity userEntity = new UseLoginEntity();
        userEntity.setPassword(newPwd);
        userEntity.setMobile(mobile);
        boolean rst = userFeign.updateUserById(userEntity);
        if (rst) {
            return R.ok();
        } else {
            return R.error("修改失败");
        }
    }
}

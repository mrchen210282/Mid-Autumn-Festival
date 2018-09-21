package cn.bitflash.vip.user.controller;


import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserEntity;
import cn.bitflash.interceptor.ApiLoginInterceptor;
import cn.bitflash.util.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.Api;
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
    public R updateLoginPwd(@RequestAttribute(ApiLoginInterceptor.UID) String uid, @RequestParam String oldPwd, @RequestParam String newPwd) {
        UserEntity user = userFeign.selectUserByUid(uid);
        if (oldPwd.equals(user.getPassword())) {
            user.setPassword(newPwd);
            userFeign.updateUserById(user);
            return R.ok();
        } else {
            return R.error("原密码不正确");
        }
    }

    @PostMapping("findLoginPwd")
    public R findLoginPwd(@RequestParam String mobile, @RequestParam String newPwd) {
        UserEntity userEntity = new UserEntity();
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

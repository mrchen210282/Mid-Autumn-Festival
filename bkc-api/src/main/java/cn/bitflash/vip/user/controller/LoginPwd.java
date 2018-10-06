package cn.bitflash.vip.user.controller;


import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.interceptor.ApiLoginInterceptor;
import cn.bitflash.utils.Encrypt;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.RandomStringUtils;
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
    public R updateLoginPwd(@RequestAttribute(ApiLoginInterceptor.UID) String uid, @ApiParam @RequestParam String oldPwd
            , @ApiParam @RequestParam String newPwd) {
        UserSecretEntity user = userFeign.selectUserLoginByUid(uid);
        String oldPasswd = Encrypt.SHA256(oldPwd + user.getSalt());
        if (oldPasswd.equals(user.getPassword())) {
            String salt = RandomStringUtils.randomAlphanumeric(16);
            user.setSalt(salt);
            user.setPassword(Encrypt.SHA256(newPwd + salt));
            userFeign.updateUserById(user);
            return R.ok();
        } else {
            return R.error("原密码不正确");
        }
    }

    @PostMapping("findLoginPwd")
    @ApiOperation("找回登录密码")
    public R findLoginPwd(@ApiParam @RequestParam String mobile, @ApiParam @RequestParam String newPwd) {
        UserSecretEntity userEntity = new UserSecretEntity();
        String salt = RandomStringUtils.randomAlphanumeric(16);
        userEntity.setSalt(salt);
        userEntity.setPassword(Encrypt.SHA256(newPwd + salt));
        userEntity.setMobile(mobile);
        boolean rst = userFeign.updateUserByMobile(userEntity);
        if (rst) {
            return R.ok();
        } else {
            return R.error("修改失败");
        }
    }
}

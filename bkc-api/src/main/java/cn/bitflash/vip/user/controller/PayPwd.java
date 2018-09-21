package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserEntity;
import cn.bitflash.entity.UserPayPwdEntity;
import cn.bitflash.util.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
@Api(value = "交易密码Con", tags = {"修改，找回"})
public class PayPwd {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("vaildatePayPwd")
    public R vaildatePayPwd(@RequestParam String oldPwd, @RequestParam String loginPwd, @RequestAttribute("uid") String uid) {
        UserEntity userEntity = userFeign.selectUserByUid(uid);
        if (!userEntity.getPassword().equals(loginPwd)) {
            return R.error(501, "登录密码错误");
        }
        UserPayPwdEntity userPayPwdEntity = userFeign.selectUserPwdByUid(uid);
        if (!oldPwd.equals(userPayPwdEntity.getPayPassword())) {
            return R.error(502, "交易密码错误");
        }
        return R.ok();

    }

    @Login
    @PostMapping("updatePayPwd")
    public R updatePayPwd(@RequestParam String newPwd,@RequestAttribute("uid")String uid ) {
        UserPayPwdEntity userPayPwdEntity = userFeign.selectUserPwdByUid(uid);
        //第一次添加交易密码
        if (userPayPwdEntity == null) {
            userPayPwdEntity = new UserPayPwdEntity();
            userPayPwdEntity.setUid(uid);
            userPayPwdEntity.setCreateTime(new Date());
            userPayPwdEntity.setPayPassword(newPwd);
            userFeign.insertUserPwd(userPayPwdEntity);
            return R.ok();
        }
        //更新交易密码
        userPayPwdEntity.setPayPassword(newPwd);
        userFeign.updateUserPwdById(userPayPwdEntity);
        return R.ok();
    }

}

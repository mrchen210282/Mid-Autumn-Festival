package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payPassword")
public class PayPassword {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("updatePayPasswd")
    @ApiOperation("更新交易密码")
    public R updatePaypassword(@RequestAttribute("uid") String uid, @RequestParam("oldPasswd") String oldPasswd,
                               @RequestParam("newPasswd") String newPasswd) {
        UserSecretEntity secretEntity = userFeign.selectUserLoginByUid(uid);
        if(!secretEntity.getPayPassword().equals(oldPasswd)){
            return R.error("密码错误，修改失败");
        }
        secretEntity.setPayPassword(newPasswd);
        secretEntity.setIsSetPaypwd(Common.AUTHENTICATION);
        userFeign.updateUserById(secretEntity);
        return R.ok();
    }

}

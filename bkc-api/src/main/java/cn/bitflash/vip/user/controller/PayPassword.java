package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.Encrypt;
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
    public R updatePaypassword(@RequestAttribute("uid") String uid, @RequestParam(value = "oldPasswd", required = false) String oldPasswd,
                               @RequestParam("newPasswd") String newPasswd,
                               @RequestParam(value ="loginPasswd",required = false)String login) {
        UserSecretEntity secretEntity = userFeign.selectUserLoginByUid(uid);
        if (oldPasswd != null) {
            if (!secretEntity.getPayPassword().equals(oldPasswd)) {
                return R.error("修改失败：原交易密码错误");
            }
        }
        if(login!=null){ 
            if(!secretEntity.getPassword().equals(Encrypt.SHA256(login+secretEntity.getSalt()))){
                return R.error("修改失败：登录密码错误");
            }
        }
        secretEntity.setPayPassword(newPasswd);
        secretEntity.setIsSetPaypwd(Common.AUTHENTICATION);
        userFeign.updateUserById(secretEntity);
        return R.ok();
    }

}

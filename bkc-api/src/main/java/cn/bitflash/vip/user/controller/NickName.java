package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nickname")
public class NickName {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("updateNickname")
    @ApiOperation("修改昵称")
    public R updateNicknmae(@RequestAttribute("uid")String uid , @RequestParam("nickname")String nickname){
        UserInfoEntity info = userFeign.selectUserinfoById(uid);
        if(info.getNicklock().equals(Common.AUTHENTICATION)){
            return R.error("昵称只能修改一次");
        }
        info.setNicklock(nickname);
        info.setNicklock(Common.AUTHENTICATION);
        userFeign.updateUserInfoById(info);
        return R.ok();
    }
}

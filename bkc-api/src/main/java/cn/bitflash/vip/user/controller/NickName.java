package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.util.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(value = "昵称Con", tags = {"修改"})
public class NickName {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("updateNickName")
    public R updateNickName(@RequestParam String nickname, @RequestAttribute("uid") String uid) {
        if (nickname.length() <= 6) {
            String nicklock = userFeign.selectUserInfoByColumn(uid, "nicklock");
            if ("1".equals(nicklock)) {
                return R.error("昵称不能修改！");
            } else {
                UserInfoEntity userinfo = new UserInfoEntity();
                userinfo.setNickname(nickname);
                userinfo.setUid(uid);
                userinfo.setNicklock("1");
                userFeign.updateUserInfoById(userinfo);
                return R.ok();
            }
        } else {
            return R.error("昵称长度不能大于6个字！");
        }
    }
}

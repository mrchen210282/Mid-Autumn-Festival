package cn.bitflash.vip.user.controller;


import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserBankPaymentInfoEntity;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserMobilePaymentInfoEntity;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("validate")
public class Validate {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("getUserPower")
    @ApiOperation("获取用户权限信息")
    public R getUserPower(@RequestAttribute("uid") String uid) {
        UserInfoEntity info = userFeign.selectUserinfoById(uid);
        List<UserMobilePaymentInfoEntity> mobiles = userFeign.selectPaymentsByUid(uid);
        UserBankPaymentInfoEntity bank = userFeign.selectBankInfoByUid(uid);

        Map<String, Object> map = new HashMap<>();
        //是否有支付方式
        map.put("payment", Common.UNAUTHENTICATION);
        if (mobiles != null || bank != null) {
            map.put("payment", Common.AUTHENTICATION);
        }
        //真实姓名
        map.put("name", info.getRealname());
        //是否是邀请码注册
        map.put("invited", info.getIsInvited());
        //是否实名认证
        map.put("auth", info.getIsAuth());
        //当前算力
        map.put("level", info.getPowerLevel());
        //uid
        map.put("uid", uid);

        return R.ok(map);

    }


}

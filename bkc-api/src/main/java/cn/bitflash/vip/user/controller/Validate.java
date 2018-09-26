package cn.bitflash.vip.user.controller;


import cn.bitflash.entity.UserCashAssetsEntity;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("validate")
public class Validate {

    @Autowired
    private UserFeign userFeign;

    @PostMapping("getUserPower")
    @ApiOperation("获取用户权限信息")
    public R getUserPower(@RequestAttribute("uid")String uid){

        UserInfoEntity info = userFeign.selectUserinfoById(uid);
        Map<String,Object> map= new HashMap<>();
        map.put("name",info.getRealname());
        map.put("invited",info.getIsInvitated());
        map.put("auth",info.getIsAuth());
        UserCashAssetsEntity cash = userFeign.selectCashAssetsByUid(uid);
        map.put("level",cash.getLevel());
        return R.ok(map);

    }



}

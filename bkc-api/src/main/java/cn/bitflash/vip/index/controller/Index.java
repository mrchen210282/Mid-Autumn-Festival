package cn.bitflash.vip.index.controller;

import cn.bitflash.entity.UserLoginEntity;
import cn.bitflash.utils.*;
import cn.bitflash.vip.index.entity.LoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/indexAssets")
@Api(value = "首页资产情况", tags = {"首页资产情况"})
public class Index {


    @PostMapping("assets")
    @ApiOperation("首页资产")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "uid", dataType = "String"),
    })
    public R assets(@RequestParam("uid") String uid) {

        return R.error("手机号或密码错误");
    }
}

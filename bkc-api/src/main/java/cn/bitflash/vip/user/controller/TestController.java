package cn.bitflash.vip.user.controller;

import cn.bitflash.util.R;
import cn.bitflash.vip.user.feign.SystemUidFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private SystemUidFeign systemUidFeign;

    @PostMapping("selectUid")
    @ApiOperation("修改昵称")
    public R selectUid() {
        String uid = systemUidFeign.selectUid();
        System.out.println(uid + "-------------------------");
        return R.ok();
    }
}

package cn.bitflash.vip.user.controller;

import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.SystemUidFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SystemUidFeign systemUidFeign;

    @PostMapping("selectUid")
    @ApiOperation("修改昵称")
    public R selectUid(@RequestParam("mobile")String mobile, @RequestParam("pwd") String pwd) {

        String uid = systemUidFeign.selectUid(mobile,pwd);
        System.out.println(uid + "-------------------------");
        return R.ok();
    }
}

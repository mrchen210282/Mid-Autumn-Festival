package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserEntity;
import cn.bitflash.interceptor.ApiLoginInterceptor;
import cn.bitflash.util.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(value = "钱包地址Con", tags = {"查看"})
public class Wallet {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("getWalletToken")
    public R getWalletToken(@RequestAttribute(ApiLoginInterceptor.UID) String uid) {
        UserEntity userEntity = userFeign.selectUserByUid(uid);
        return R.ok(userEntity.getUuid());
    }
}

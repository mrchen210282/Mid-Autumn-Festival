package cn.bitflash.vip.index.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.SystemAppBannerEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.index.feign.IndexFeign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appBanner")
@Api(value = "首页轮播广告", tags = {"首页轮播广告"})
public class AppBannerControlller {

    @Autowired
    private IndexFeign indexFeign;

    /**
     * 首页轮播图片
     * @return
     */
    @Login
    @PostMapping("selectAppBanner")
    public R selectAppBanner() {
        List<SystemAppBannerEntity> list = indexFeign.selectAppBanner();
        return R.ok().put("list",list);
    }
}

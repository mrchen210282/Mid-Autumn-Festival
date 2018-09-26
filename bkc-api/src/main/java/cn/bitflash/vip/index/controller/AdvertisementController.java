package cn.bitflash.vip.index.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.SystemAdvertisementEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.index.feign.IndexFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/advertisement")
@Api(value = "首页轮播广告", tags = {"首页轮播广告"})
public class AdvertisementController {

    @Autowired
    private IndexFeign indexFeign;

    /**
     * 按上传日期返回最新的三张上传图片
     * selectSystemAdvertisement
     * @return
     */
    @Login
    @PostMapping("selectSystemAdvertisement")
    @ApiOperation("查询轮播图片")
    public R selectSystemAdvertisement() {
        List<SystemAdvertisementEntity> list = indexFeign.selectSystemAdvertisement();
        return R.ok().put("list",list);
    }


    /**
     * 上传图片
     * selectSystemAdvertisement
     * @return
     */
    @Login
    @PostMapping("insertSystemAdvertisement")
    @ApiOperation("查询轮播图片")
    public R insertSystemAdvertisement(@RequestParam("img")String img) {
        Boolean bol = indexFeign.insertSystemAdvertisement(img);
        return R.ok();
    }
}




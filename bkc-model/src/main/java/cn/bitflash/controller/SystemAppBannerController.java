package cn.bitflash.controller;

import cn.bitflash.entity.SystemAppBannerEntity;
import cn.bitflash.service.SystemAppBannerService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class SystemAppBannerController {

    @Autowired
    private SystemAppBannerService systemAppBannerService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/systemAppBanner/selectById")
    public SystemAppBannerEntity selectById(@RequestParam("id") String id) {
        SystemAppBannerEntity entity = systemAppBannerService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/systemAppBanner/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        SystemAppBannerEntity entity = (SystemAppBannerEntity) JSONObject.parseObject(json.toString(), SystemAppBannerEntity.class);
        return systemAppBannerService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/systemAppBanner/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        SystemAppBannerEntity entity = (SystemAppBannerEntity) JSONObject.parseObject(json.toString(), SystemAppBannerEntity.class);
        return systemAppBannerService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/systemAppBanner/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return systemAppBannerService.deleteById(id);
    }

}

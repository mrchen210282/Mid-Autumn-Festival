package cn.bitflash.controller;

import cn.bitflash.entity.SystemAppBannerEntity;
import cn.bitflash.service.SystemAppBannerService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * selectAppBanner
     *
     * @return
     */
    @PostMapping("/inner/systemAppBanner/selectAppBanner")
    public List<SystemAppBannerEntity> selectAppBanner() {
        return systemAppBannerService.selectAppBanner();
    }

    /**
     * admin
     * apiBannerList
     */
    @GetMapping("/inner/systemAppBanner/apiBannerList")
    public List<SystemAppBannerEntity> apiBannerList() {
        return systemAppBannerService.selectList(new EntityWrapper<>());
    }

    /**
     * admin
     * apiBannerAdd
     */
    @PostMapping(value = "/inner/systemAppBanner/apiBannerAdd",consumes = "application/json")
    public Boolean apiBannerAdd(@RequestBody SystemAppBannerEntity entity) {
        return systemAppBannerService.insert(entity);
    }

    /**
     * admin
     * apiBannerDelete
     */
    @GetMapping(value = "/inner/systemAppBanner/apiBannerDelete/{ids}")
    public Boolean apiBannerDelete(@PathVariable String ids) {
        return systemAppBannerService.deleteById(Integer.parseInt(ids));
    }

    /**
     * admin
     * apiBannerInfo
     */
    @GetMapping("/inner/systemAppBanner/apiBannerList/{id}")
    public SystemAppBannerEntity apiBannerInfo(@PathVariable String id) {
        return systemAppBannerService.selectById(Integer.parseInt(id));
    }

}

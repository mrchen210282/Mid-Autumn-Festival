package cn.bitflash.controller;

import cn.bitflash.bean.ImgForm;
import cn.bitflash.entity.SystemAppBannerEntity;
import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.service.SystemAppBannerService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/inner/systemAppBanner/mobileBannerSetting")
    public Map<String,Object> uploadPayment(@RequestBody ImgForm imgForm) {
        String imgPath = "192.168.31.43:8888/banner/";
        String path = "/home/statics/banner/";
        String imgName = RandomStringUtils.randomAlphanumeric(10);
        path = path+imgName;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            String[] base64Str = imgForm.getImg().split(",");
            if (base64Str.length >= 2) {
                byte[] b = decoder.decodeBuffer(base64Str[1]);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {// 调整异常数据
                        b[i] += 256;
                    }
                }
                // 生成jpeg图片
                OutputStream out = new FileOutputStream(path);
                out.write(b);
                out.flush();
                out.close();
            } else {
                return new ModelMap("status",500);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelMap("status",500);
        }

        SystemAppBannerEntity appBannerEntity = new SystemAppBannerEntity();
        appBannerEntity.setId(imgForm.getId());
        appBannerEntity.setBannerAddress(imgPath+imgName);
        appBannerEntity.setCreateTime(new Date());
        appBannerEntity.setOpendAddress(imgForm.getOpenAddress());
        appBannerEntity.setTitle(imgForm.getTitle());
        systemAppBannerService.insertOrUpdate(appBannerEntity);
        return new ModelMap("status",0);
    }



}

package cn.bitflash.controller;

import cn.bitflash.entity.PriceLinechartEntity;
import cn.bitflash.entity.SystemAdvertisementEntity;
import cn.bitflash.service.PriceLinechartService;
import cn.bitflash.service.SystemAdvertisementService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangjun
 *
 */
@RestController
public class SystemAdvertisementController {


    @Autowired
    private SystemAdvertisementService systemAdvertisementService;

    /**
     * 按上传日期返回最新的三张上传图片
     * selectSystemAdvertisement
     * @return
     */
    @PostMapping("/inner/systemAdvertisement/selectSystemAdvertisement")
    public List<SystemAdvertisementEntity> selectSystemAdvertisement() {
        return systemAdvertisementService.selectSystemAdvertisement();
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/systemAdvertisement/insertSystemAdvertisement")
    public boolean insertSystemAdvertisement(@RequestBody JSONObject json) throws Exception {
        SystemAdvertisementEntity entity = (SystemAdvertisementEntity) JSONObject.parseObject(json.toString(), SystemAdvertisementEntity.class);
        return systemAdvertisementService.insert(entity);
    }
}

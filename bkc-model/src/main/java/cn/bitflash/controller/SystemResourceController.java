package cn.bitflash.controller;


import cn.bitflash.entity.SystemResourceEntity;
import cn.bitflash.service.SystemResourceService;
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
public class SystemResourceController {

    @Autowired
    private SystemResourceService systemResourceService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/systemResource/selectById")
    public SystemResourceEntity selectById(@RequestParam("id") String id) {
        SystemResourceEntity entity = systemResourceService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/systemResource/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        SystemResourceEntity entity = (SystemResourceEntity) JSONObject.parseObject(json.toString(), SystemResourceEntity.class);
        return systemResourceService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/systemResource/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        SystemResourceEntity entity = (SystemResourceEntity) JSONObject.parseObject(json.toString(), SystemResourceEntity.class);
        return systemResourceService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/systemResource/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return systemResourceService.deleteById(id);
    }

    /**
     * getPath
     *
     * @return
     */
    @PostMapping("/inner/systemResource/getPath")
    public String getPath(@RequestParam("id") String id) {
        String path = systemResourceService.selectById(id).getPath();
        return path;
    }

}

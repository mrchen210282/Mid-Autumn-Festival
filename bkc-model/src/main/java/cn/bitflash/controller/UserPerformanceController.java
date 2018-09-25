package cn.bitflash.controller;


import cn.bitflash.entity.UserPerformanceEntity;
import cn.bitflash.service.UserPerformanceService;
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
public class UserPerformanceController {

    @Autowired
    private UserPerformanceService userPerformanceService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userPerformance/selectById")
    public UserPerformanceEntity selectById(@RequestParam("id") String id) {
        UserPerformanceEntity entity = userPerformanceService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userPerformance/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserPerformanceEntity entity = (UserPerformanceEntity) JSONObject.parseObject(json.toString(), UserPerformanceEntity.class);
        return userPerformanceService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userPerformance/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserPerformanceEntity entity = (UserPerformanceEntity) JSONObject.parseObject(json.toString(), UserPerformanceEntity.class);
        return userPerformanceService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userPerformance/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userPerformanceService.deleteById(id);
    }

}

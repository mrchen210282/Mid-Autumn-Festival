package cn.bitflash.controller;


import cn.bitflash.entity.SystemParamEntity;
import cn.bitflash.service.SystemParamService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author GAOYGUUO
 */
@RestController
public class SystemParamController {

    @Autowired
    private SystemParamService systemParamService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/systemParam/selectById")
    public SystemParamEntity selectById(@RequestParam("id") String id) {
        SystemParamEntity entity = systemParamService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/systemParam/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        SystemParamEntity entity = (SystemParamEntity) JSONObject.parseObject(json.toString(), SystemParamEntity.class);
        return systemParamService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/systemParam/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        SystemParamEntity entity = (SystemParamEntity) JSONObject.parseObject(json.toString(), SystemParamEntity.class);
        return systemParamService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/systemParam/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return systemParamService.deleteById(id);
    }

//    @PostMapping("/inner/systemParam/getVal")
//    public String getVal(@RequestParam("key")String key){
//        return systemParamService.selectOne(new EntityWrapper<SystemParamEntity>().eq("param",key)).getValue();
//    }

    @GetMapping("/inner/systemParam/getVal/{key}")
    public String getVal2(@PathVariable("key")String key){
        return systemParamService.selectOne(new EntityWrapper<SystemParamEntity>().eq("param",key)).getValue();
    }

}

package cn.bitflash.controller;


import cn.bitflash.entity.SystemParamEntity;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.service.SystemParamService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/inner/systemParam/getVal/{key}")
    public String getVal2(@PathVariable("key")String key){
        return systemParamService.selectOne(new EntityWrapper<SystemParamEntity>().eq("param",key)).getValue();
    }



    /**
     * admin
     * apiParamList
     */
    @GetMapping("inner/systemParam/apiParamList/{page}")
    public List<SystemParamEntity> apiParamList(@PathVariable String page){
        return systemParamService.apiParamList(Integer.parseInt(page));
    }

    /**
     * admin
     * paramListCount
     */
    @GetMapping("inner/systemParam/paramListCount")
    public Integer paramListCount(){
        return systemParamService.paramListCount();
    }

    /**
     * admin
     * getParam
     */
    @GetMapping("inner/systemParam/getParam/{id}")
    public SystemParamEntity getParam(@PathVariable String id){
        return systemParamService.selectOne(new EntityWrapper<SystemParamEntity>().eq("id",Integer.parseInt(id)));
    }

    /**
     * admin
     * findByName
     */
    @GetMapping("inner/systemParam/findByName/{name}")
    public SystemParamEntity findByName(@PathVariable String name){
        return systemParamService.selectOne(new EntityWrapper<SystemParamEntity>().eq("param",name));
    }

    /**
     * admin
     * updateParam
     */
    @PostMapping(value = "inner/systemParam/updateParam", consumes = "application/json")
    public Boolean updateParam(@RequestBody SystemParamEntity entity){
        return systemParamService.updateById(entity);
    }

    /**
     * admin
     * addParam
     */
    @PostMapping(value = "inner/systemParam/addParam", consumes = "application/json")
    public Boolean addParam(@RequestBody SystemParamEntity entity){
        return systemParamService.insert(entity);
    }

    /**
     * admin
     * deleteParam
     */
    @GetMapping("inner/systemParam/deleteParam/{id}")
    public Boolean deleteParam(@PathVariable String id){
        return systemParamService.deleteById(Integer.parseInt(id));
    }

}

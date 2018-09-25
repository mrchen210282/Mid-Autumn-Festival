package cn.bitflash.controller;


import cn.bitflash.entity.DictUserRelationEntity;
import cn.bitflash.service.DictUserRelationService;
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
public class DictUserRelationController {

    @Autowired
    private DictUserRelationService dictUserRelationService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/dictUserRelation/selectById")
    public DictUserRelationEntity selectById(@RequestParam("id") String id) {
        DictUserRelationEntity entity = dictUserRelationService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/dictUserRelation/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        DictUserRelationEntity entity = (DictUserRelationEntity) JSONObject.parseObject(json.toString(), DictUserRelationEntity.class);
        return dictUserRelationService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/dictUserRelation/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        DictUserRelationEntity entity = (DictUserRelationEntity) JSONObject.parseObject(json.toString(), DictUserRelationEntity.class);
        return dictUserRelationService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/dictUserRelation/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return dictUserRelationService.deleteById(id);
    }

}

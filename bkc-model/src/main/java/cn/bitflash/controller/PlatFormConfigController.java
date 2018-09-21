package cn.bitflash.controller;


import cn.bitflash.entity.PlatformConfigEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.PlatFormConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
public class PlatFormConfigController {

    @Autowired
    private PlatFormConfigService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public PlatformConfigEntity selectOne(Map<String, Object> param) {
        List<PlatformConfigEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            PlatformConfigEntity entity = entityList.get(0);
            return entity;
        }
        return null;
    }

    /**
     * updateById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(PlatformConfigEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(PlatformConfigEntity entity) {
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(int id) {
        service.deleteById(id);
    }

    /**
     * getVal
     *
     * @return
     */
    @PostMapping("/inner/platFormConfig/getVal")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public String getVal(String key) {
        String val = service.getVal(key);
        return val;
    }

}

package cn.bitflash.controller;


import cn.bitflash.entity.PriceLinechartEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.PriceLinechartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
public class PriceLinechartController {

    @Autowired
    private PriceLinechartService service;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public PriceLinechartEntity selectOne(Map<String, Object> param) {
        List<PriceLinechartEntity> entityList = service.selectByMap(param);
        if (entityList.size() > 0) {
            PriceLinechartEntity entity = entityList.get(0);
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
    public void updateById(PriceLinechartEntity entity) {
        service.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(PriceLinechartEntity entity) {
        service.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(PriceLinechartEntity entity) {
        service.deleteById(entity);
    }

}

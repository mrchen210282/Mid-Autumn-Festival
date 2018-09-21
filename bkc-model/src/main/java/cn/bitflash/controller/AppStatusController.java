package cn.bitflash.controller;


import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.AppStatusService;
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
public class AppStatusController {

    @Autowired
    private AppStatusService appStatusService;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public AppStatusEntity selectOne(Map<String, Object> param) {
        List<AppStatusEntity> appStatusEntities = appStatusService.selectByMap(param);
        if (appStatusEntities.size() > 0) {
            AppStatusEntity appStatusEntity = appStatusEntities.get(0);
            return appStatusEntity;
        }
        return null;
    }

    /**
     * updateById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(AppStatusEntity appStatusEntity) {
        appStatusService.updateById(appStatusEntity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(AppStatusEntity appStatusEntity) {
        appStatusService.insert(appStatusEntity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(String id) {
        appStatusService.deleteById(id);
    }

}

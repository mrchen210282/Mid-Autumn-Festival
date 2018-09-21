package cn.bitflash.controller;


import cn.bitflash.entity.ComplaintUploadEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.ComplaintUploadService;
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
public class ComplaintUploadController {

    @Autowired
    private ComplaintUploadService complaintUploadService;

    /**
     * selectOne
     *
     * @param param
     * @return
     */

    public ComplaintUploadEntity selectOne(Map<String, Object> param) {
        List<ComplaintUploadEntity> complaintUploadEntities = complaintUploadService.selectByMap(param);
        if (complaintUploadEntities.size() > 0) {
            ComplaintUploadEntity complaintUploadEntity = complaintUploadEntities.get(0);
            return complaintUploadEntity;
        }
        return null;
    }

    /**
     * updateById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(ComplaintUploadEntity complaintUploadEntity) {
        complaintUploadService.updateById(complaintUploadEntity);
    }

    /**
     * insert
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(ComplaintUploadEntity complaintUploadEntity) {
        complaintUploadService.insert(complaintUploadEntity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(int id) {
        complaintUploadService.deleteById(id);
    }

}

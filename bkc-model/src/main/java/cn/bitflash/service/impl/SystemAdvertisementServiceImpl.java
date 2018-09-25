package cn.bitflash.service.impl;

import cn.bitflash.dao.DictUserRelationDao;
import cn.bitflash.dao.SystemAdvertisementDao;
import cn.bitflash.entity.DictUserRelationEntity;
import cn.bitflash.entity.SystemAdvertisementEntity;
import cn.bitflash.service.DictUserRelationService;
import cn.bitflash.service.SystemAdvertisementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjun
 * @date 2018年9月25日
 */
@Service("systemAdvertisementService")
public class SystemAdvertisementServiceImpl extends ServiceImpl<SystemAdvertisementDao, SystemAdvertisementEntity> implements SystemAdvertisementService {

    //查询最新的三张图片
    public List<SystemAdvertisementEntity> selectSystemAdvertisement() {
        return baseMapper.selectSystemAdvertisement();
    }
}

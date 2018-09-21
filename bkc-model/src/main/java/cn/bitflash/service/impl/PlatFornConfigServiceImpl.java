package cn.bitflash.service.impl;

import cn.bitflash.entity.PlatformConfigEntity;
import cn.bitflash.dao.PlatFormConfigDao;
import cn.bitflash.service.PlatFormConfigService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("platFornConfigService")
public class PlatFornConfigServiceImpl extends ServiceImpl<PlatFormConfigDao, PlatformConfigEntity> implements PlatFormConfigService {


    @Override
    public String getVal(String key) {
        String val = baseMapper.getValue(key);
        return val;
    }
}

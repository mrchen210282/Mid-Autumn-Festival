package cn.bitflash.service;

import cn.bitflash.entity.SystemAdvertisementEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author wangjun
 * @date 2018年9月25日
 */
public interface SystemAdvertisementService extends IService<SystemAdvertisementEntity> {

    //查询最新的三张图片
    public List<SystemAdvertisementEntity> selectSystemAdvertisement();
}

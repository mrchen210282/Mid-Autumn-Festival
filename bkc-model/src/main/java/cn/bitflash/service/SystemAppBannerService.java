package cn.bitflash.service;

import cn.bitflash.entity.SystemAppBannerEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface SystemAppBannerService extends IService<SystemAppBannerEntity> {

    public List<SystemAppBannerEntity> selectAppBanner();
}

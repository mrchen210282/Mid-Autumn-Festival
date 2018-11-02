package cn.bitflash.service;

import cn.bitflash.bean.UserAssetsHlbBean;
import cn.bitflash.entity.UserAssetsHlbEntity;
import com.baomidou.mybatisplus.service.IService;

public interface UserAssetsHlbService extends IService<UserAssetsHlbEntity> {

    UserAssetsHlbBean selectHlbCommunity(String uid);
}

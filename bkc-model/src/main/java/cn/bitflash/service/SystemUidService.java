package cn.bitflash.service;


import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.entity.SystemUidEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author wangjun
 * @date 2018年9月22日 下午5:06:07
 */

public interface SystemUidService extends IService<SystemUidEntity> {

    //查询用户uid
    public String selectUid();
}

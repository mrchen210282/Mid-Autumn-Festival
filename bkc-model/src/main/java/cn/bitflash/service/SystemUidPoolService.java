package cn.bitflash.service;


import cn.bitflash.entity.DictUserRelationEntity;
import cn.bitflash.entity.SystemUidPoolEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author wangjun
 * @date 2018年9月22日
 */
public interface SystemUidPoolService extends IService<SystemUidPoolEntity> {

    //取得用户id
    public String selectUid();
}

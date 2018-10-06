package cn.bitflash.dao;

import cn.bitflash.bean.UserCashAssetsJoinDictComputingPowerBean;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author wangjun
 * @date 2018年9月25日
 */
public interface UserCashAssetsJoinDictComputingPowerDao extends BaseMapper<UserCashAssetsJoinDictComputingPowerBean> {

    public UserCashAssetsJoinDictComputingPowerBean selectUserCashAssetsJoinDictComputingPower(String uid);
}

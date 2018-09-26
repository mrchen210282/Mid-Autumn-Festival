package cn.bitflash.service;

import cn.bitflash.bean.UserCashAssetsJoinDictComputingPowerBean;
import cn.bitflash.entity.UserBankPaymentInfoEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author wangjun
 * @date 2018年9月25日
 */
public interface UserCashAssetsJoinDictComputingPowerService extends IService<UserCashAssetsJoinDictComputingPowerBean> {

    public UserCashAssetsJoinDictComputingPowerBean selectUserCashAssetsJoinDictComputingPower(String uid);
}

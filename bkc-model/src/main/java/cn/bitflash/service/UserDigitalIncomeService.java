package cn.bitflash.service;

import cn.bitflash.entity.UserDigitalIncomeEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author soso
 * @date 2018年5月21日 下午4:47:36
 */

public interface UserDigitalIncomeService extends IService<UserDigitalIncomeEntity> {

    void updateUserAccountByParam(UserDigitalIncomeEntity userDigitalIncomeEntity);

}

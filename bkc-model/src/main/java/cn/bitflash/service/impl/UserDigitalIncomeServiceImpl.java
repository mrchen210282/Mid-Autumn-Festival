package cn.bitflash.service.impl;

import cn.bitflash.dao.UserDigitalIncomeDao;
import cn.bitflash.entity.UserDigitalIncomeEntity;
import cn.bitflash.service.UserDigitalIncomeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author soso
 * @date 2018年5月21日 下午4:48:48
 */

@Service("userDigitalIncomeService")
public class UserDigitalIncomeServiceImpl extends ServiceImpl<UserDigitalIncomeDao, UserDigitalIncomeEntity> implements UserDigitalIncomeService {

    @Override
    public void updateUserAccountByParam(UserDigitalIncomeEntity userDigitalIncomeEntity) {
        baseMapper.updateUserAccountByParam(userDigitalIncomeEntity);
    }
}

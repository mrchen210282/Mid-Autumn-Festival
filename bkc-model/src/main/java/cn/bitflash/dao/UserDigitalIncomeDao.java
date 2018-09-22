package cn.bitflash.dao;

import cn.bitflash.entity.UserDigitalIncomeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author soso
 * @date 2018年5月21日 下午4:45:51
 */
@Repository
public interface UserDigitalIncomeDao extends BaseMapper<UserDigitalIncomeEntity> {

    void updateUserAccountByParam(UserDigitalIncomeEntity userDigitalIncomeEntity);

}

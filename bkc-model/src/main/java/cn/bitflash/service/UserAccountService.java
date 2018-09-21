package cn.bitflash.service;

import cn.bitflash.entity.UserAccountBean;
import cn.bitflash.entity.UserAccountEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * @author soso
 * @date 2018年5月21日 下午4:47:36
 */

public interface UserAccountService extends IService<UserAccountEntity> {

    void updateUserAccountByParam(UserAccountEntity userAccountEntity);

    UserAccountBean selectUserAccount(Map<String, Object> map);

}

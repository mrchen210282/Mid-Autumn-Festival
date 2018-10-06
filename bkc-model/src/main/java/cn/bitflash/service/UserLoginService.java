package cn.bitflash.service;


import cn.bitflash.entity.UserSecretEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface UserLoginService extends IService<UserSecretEntity> {

    String selectUid(Map<String, Object> map);

}

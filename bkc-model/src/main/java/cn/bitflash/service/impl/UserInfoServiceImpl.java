package cn.bitflash.service.impl;

import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.dao.UserInfoDao;
import cn.bitflash.service.UserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {


}

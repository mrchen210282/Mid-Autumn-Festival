package cn.bitflash.service.impl;

import cn.bitflash.entity.UserPayPwdEntity;
import cn.bitflash.dao.UserPayPwdDao;
import cn.bitflash.service.UserPayPwdService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("userPayPwdService")
public class UserPayPwdServiceImpl extends ServiceImpl<UserPayPwdDao, UserPayPwdEntity> implements UserPayPwdService {
}

package cn.bitflash.service.impl;

import cn.bitflash.entity.UserEntity;
import cn.bitflash.dao.LoginDao;
import cn.bitflash.service.LoginService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl extends ServiceImpl<LoginDao, UserEntity> implements LoginService {
}

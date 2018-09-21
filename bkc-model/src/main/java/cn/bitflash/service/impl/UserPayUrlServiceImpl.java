package cn.bitflash.service.impl;

import cn.bitflash.entity.UserPayUrlEntity;
import cn.bitflash.dao.UserPayUrlDao;
import cn.bitflash.service.UserPayUrlService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("userPayUrlService")
public class UserPayUrlServiceImpl extends ServiceImpl<UserPayUrlDao, UserPayUrlEntity> implements UserPayUrlService {

}

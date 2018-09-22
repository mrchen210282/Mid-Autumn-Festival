package cn.bitflash.service.impl;

import cn.bitflash.dao.UserPayImgDao;
import cn.bitflash.entity.UserPayImgEntity;
import cn.bitflash.service.UserPayImgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("userPayImgService")
public class UserPayImgServiceImpl extends ServiceImpl<UserPayImgDao, UserPayImgEntity> implements UserPayImgService {

}

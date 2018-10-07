package cn.bitflash.service.impl;

import cn.bitflash.dao.UserBrokerageDao;
import cn.bitflash.entity.UserBrokerageEntity;
import cn.bitflash.service.UserBrokerageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author wangjun
 * @date 2018年10月06日
 */
@Service("userBrokerageService")
public class UserBrokerageServiceImpl extends ServiceImpl<UserBrokerageDao, UserBrokerageEntity> implements UserBrokerageService {
}

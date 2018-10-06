package cn.bitflash.service.impl;

import cn.bitflash.dao.UserNpcHistoryDao;
import cn.bitflash.entity.UserNpcTradeHistoryEntity;
import cn.bitflash.service.UserNpcTradeHistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("userNpcTradeHistoryService")
public class UserNpcTradeHistoryServiceImpl extends ServiceImpl<UserNpcHistoryDao, UserNpcTradeHistoryEntity> implements UserNpcTradeHistoryService {
}

package cn.bitflash.service.impl;

import cn.bitflash.dao.UserHlbHistoryDao;
import cn.bitflash.entity.UserHlbTradeHistoryEntity;
import cn.bitflash.service.UserHlbTradeHistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("userHlbTradeHistoryService")
public class UserHlbTradeHistoryServiceImpl extends ServiceImpl<UserHlbHistoryDao, UserHlbTradeHistoryEntity> implements UserHlbTradeHistoryService {
}

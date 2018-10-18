package cn.bitflash.service.impl;

import cn.bitflash.dao.GameAccountHistoryDao;
import cn.bitflash.entity.GameAccountHistoryEntity;
import cn.bitflash.service.GameAccountHistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author wangjun
 * @date 2018年8月21日 下午4:48:48
 */
@Service("gameAccountHistoryService" )
public class GameAccountHistoryServiceImpl extends ServiceImpl<GameAccountHistoryDao, GameAccountHistoryEntity> implements GameAccountHistoryService {

}

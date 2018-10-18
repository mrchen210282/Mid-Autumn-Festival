package cn.bitflash.service;

import cn.bitflash.bean.UserTradeDetail;
import cn.bitflash.entity.UserMarketTradeHistoryEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface UserMarketTradeHistoryService extends IService<UserMarketTradeHistoryEntity> {

    public UserTradeDetail selectTradeLog(Map<String,Object> map);
}

package cn.bitflash.service;

import cn.bitflash.bean.AllUserTradeBean;
import cn.bitflash.bean.OrderListBean;
import cn.bitflash.bean.TradeListBean;
import cn.bitflash.bean.UserTradeDetail;
import cn.bitflash.entity.UserMarketTradeEntity;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author gaoyuguo
 * @date 2018.10.11
 */
public interface UserMarketTradeService extends IService<UserMarketTradeEntity> {

    List<OrderListBean> selectOrderTrade(Map<String,Object> map);

    Integer selectOrderCount(Map<String,Object> map);

    List<TradeListBean> tradeList(Map<String,Object> map);

    Integer tradeListCount(@RequestParam Map<String,Object> map);

    AllUserTradeBean queryDetail(Map<String,Object> map);

    int selectTradePrompt(String uid);

    Map<String,Object> responseTrade(Map<String,Object> map);

    UserTradeDetail selectDetail(Map<String,Object> map);

    void cancelOrder(Map<String,Object> map);
}

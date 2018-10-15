/**
 * Copyright 2018 贝莱科技 http://www.bitflash.cn
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.bitflash.service.impl;

import cn.bitflash.bean.AllUserTradeBean;
import cn.bitflash.bean.OrderListBean;
import cn.bitflash.bean.TradeListBean;
import cn.bitflash.bean.UserTradeDetail;
import cn.bitflash.dao.UserMarketTradeDao;
import cn.bitflash.entity.UserMarketConfigEntity;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.service.UserMarketConfigService;
import cn.bitflash.service.UserMarketTradeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userMarketTradeService")
public class UserMarketTradeServiceImpl extends ServiceImpl<UserMarketTradeDao, UserMarketTradeEntity> implements UserMarketTradeService {

    @Autowired
    private UserMarketConfigService userMarketConfigService;

    @Override
    public List<OrderListBean> selectOrderTrade(Map<String, Object> map) {
        List<OrderListBean> orderListBean = baseMapper.selectOrderTrade(map);
        return orderListBean;
    }

    @Override
    public Integer selectOrderCount(Map<String, Object> map) {
        baseMapper.selectOrderCount(map);
        Integer count = 0;
        if(null != map.get("count")) {
            count = new Integer(map.get("count").toString());
        }
        return count;
    }

    @Override
    public List<TradeListBean> tradeList(Map<String, Object> map) {
        List<TradeListBean> list = baseMapper.tradeList(map);
        return list;
    }

    @Override
    public Integer tradeListCount(@RequestParam Map<String, Object> map) {
        baseMapper.tradeListCount(map);
        map.get("count");
        Integer count = 0;
        if(null != map.get("count")) {
            count = new Integer(map.get("count").toString());
        }

        return count;
    }

    @Override
    public AllUserTradeBean queryDetail(Map<String, Object> map) {
        AllUserTradeBean userTradeBean = baseMapper.queryDetail(map);
        return userTradeBean;
    }

    @Override
    public int selectTradePrompt(String uid) {
        return selectTradePrompt(uid);
    }

    @Override
    public Map<String, Object> responseTrade(Map<String, Object> param) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BigDecimal big = new BigDecimal(0);
            BigDecimal divide = new BigDecimal(0);

            List<UserMarketTradeEntity> userTradeList = baseMapper.selectTrade(param);

            if (null != userTradeList && userTradeList.size() > 0) {
                // 2.大于两条计算方式为总卖出数量除以总个数
                for (int i = 0; i < userTradeList.size(); i++) {
                    UserMarketTradeEntity userTradeEntity = userTradeList.get(i);
                    float price = userTradeEntity.getPrice();
                    big = big.add(new BigDecimal(price));
                }
                Integer size = new Integer(userTradeList.size());
                BigDecimal count = new BigDecimal(size);
                divide = big.divide(count, 2, BigDecimal.ROUND_HALF_UP);
                map.put("divide", divide);
            } else {
                // 1.如果没有卖出数量则默认参考价格为0.33
                map.put("divide", "0.33");
            }

            //查询手续费
            Integer id = 1;
            UserMarketConfigEntity userMarketConfigEntity = userMarketConfigService.selectById(id);

            if (null != userMarketConfigEntity) {
                map.put("poundage", userMarketConfigEntity.getPoundage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public UserTradeDetail selectDetail(Map<String, Object> map) {
        return baseMapper.selectDetail(map);
    }

    public void cancelOrder(Map<String, Object> map) {
        baseMapper.cancelOrder(map);
    }
}


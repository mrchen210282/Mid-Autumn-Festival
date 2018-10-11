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
import cn.bitflash.dao.UserMarketTradeDao;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.service.UserMarketTradeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service("userMarketTradeService")
public class UserMarketTradeServiceImpl extends ServiceImpl<UserMarketTradeDao, UserMarketTradeEntity> implements UserMarketTradeService {

    @Override
    public List<OrderListBean> selectOrderTrade(Map<String,Object> map) {
        List<OrderListBean> orderListBean = baseMapper.selectOrderTrade(map);
        return orderListBean;
    }
    @Override
    public Integer selectOrderCount(Map<String,Object> map) {
        Integer count = baseMapper.selectOrderCount(map);
        return count;
    }
    @Override
    public List<TradeListBean> tradeList(Map<String,Object> map) {
        List<TradeListBean> list = baseMapper.tradeList(map);
        return list;
    }
    @Override
    public Integer tradeListCount(@RequestParam Map<String,Object> map) {
        Integer integer = baseMapper.tradeListCount(map);
        return integer;
    }
    @Override
    public AllUserTradeBean queryDetail(Map<String,Object> map) {
        AllUserTradeBean userTradeBean = baseMapper.queryDetail(map);
        return userTradeBean;
    }
    @Override
    public int selectTradePrompt(String uid){
        return selectTradePrompt(uid);
    }
}

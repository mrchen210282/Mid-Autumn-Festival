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

package cn.bitflash.dao;

import cn.bitflash.bean.AllUserTradeBean;
import cn.bitflash.bean.OrderListBean;
import cn.bitflash.bean.TradeListBean;
import cn.bitflash.bean.UserTradeDetail;
import cn.bitflash.entity.UserMarketTradeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface UserMarketTradeDao extends BaseMapper<UserMarketTradeEntity> {

    List<OrderListBean> selectOrderTrade(Map<String,Object> map);

    Integer selectOrderCount(Map<String,Object> map);

    List<TradeListBean> tradeList(Map<String,Object> map);

    Integer tradeListCount(Map<String,Object> map);

    AllUserTradeBean queryDetail(Map<String,Object> map);

    List<UserMarketTradeEntity> selectTrade(Map<String,Object> map);

    UserTradeDetail selectDetail(Map<String,Object> map);
}

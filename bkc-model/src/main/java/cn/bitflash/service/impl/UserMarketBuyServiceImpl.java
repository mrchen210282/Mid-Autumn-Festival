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

import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.dao.UserComplaintDao;
import cn.bitflash.dao.UserMarketBuyDao;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.service.UserComplaintService;
import cn.bitflash.service.UserMarketBuyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service("userMarketBuyService")
public class UserMarketBuyServiceImpl extends ServiceImpl<UserMarketBuyDao, UserMarketBuyEntity> implements UserMarketBuyService {

    @Override
    public Integer showBuyingCount(String uid) {
        return baseMapper.showBuyingCount(uid);
    }

    /**
     * 交易页集合
     * @param uid
     * @param pages
     * @return
     */
    @Override
    public List<UserBuyBean> showBuying(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages){
        return baseMapper.showBuying(uid,pages);
    }

    /**
     * 订单页集合
     * @param uid
     * @param pages
     * @return
     */
    @Override
    public List<UserBuyBean> showOrder(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages){
        return  baseMapper.showOrder(uid,pages);
    }

    /**
     * 订单数量
     * @param uid
     * @return
     */
    @Override
    public Integer showOrderCount(@RequestParam("uid") String uid){
        return baseMapper.showOrderCount(uid);
    }

    /**
     * 订单详情
     * @param id
     * @return
     */
    @Override
    public UserBuyBean checkOrder(@RequestParam("id")String id){
        return  baseMapper.checkOrder(id);
    }
}

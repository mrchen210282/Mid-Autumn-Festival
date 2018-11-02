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

import cn.bitflash.bean.AdminOrderBean;
import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.bean.UserComplaintBean;
import cn.bitflash.dao.UserComplaintDao;
import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.service.UserComplaintService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userComplaintService")
public class UserComplaintServiceImpl extends ServiceImpl<UserComplaintDao, UserComplaintEntity> implements UserComplaintService {

    @Override
    public List<UserBuyBean> selectAppealList(String uid, Integer pages) {
        return baseMapper.selectAppealList(uid, pages);
    }

    @Override
    public Integer selectAppealCount(String uid) {
        return baseMapper.selectAppealCount(uid);
    }

    @Override
    public UserComplaintBean getComplaintMessage(String id) {
        return baseMapper.getComplaintMessage(id);
    }

    @Override
    public int selectAppealPrompt(String uid){
        return baseMapper.selectAppealPrompt(uid);
    }

    @Override
    public List<AdminOrderBean> apiComplaintList(Integer page){
        return baseMapper.apiComplaintList(page);
    }

    @Override
    public Integer apiComplaintCount(){
        return baseMapper.apiComplaintCount();
    }

    @Override
    public List<AdminOrderBean> apiComplaintSearch(Integer page,String id){
        return baseMapper.apiComplaintSearch(page,id);
    }
}

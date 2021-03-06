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
import cn.bitflash.bean.UserSuccessBean;
import cn.bitflash.dao.UserSuccessDao;
import cn.bitflash.service.UserSuccessService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userSuccessService")
public class UserSuccessServiceImpl extends ServiceImpl<UserSuccessDao, UserSuccessBean> implements UserSuccessService {
    @Override
    public List<UserSuccessBean> selectSuccessList(@Param("uid") String uid, @Param("pages") Integer pages){
        return baseMapper.selectSuccessList(uid,pages);
    }

    @Override
    public Integer selectSuccessCount(String uid){return baseMapper.selectSuccessCount(uid);}

    @Override
    public UserSuccessBean getSuccessMessage(@Param("id") String uid, @Param("state") String state){
        return baseMapper.getSuccessMessage(uid,state);
    }

    @Override
    public List<UserSuccessBean> apiSuccessList(Integer page){
        return baseMapper.apiSuccessList(page);
    }

    @Override
    public Integer apiSuccessListCount(){
        return baseMapper.apiSuccessListCount();
    }

    @Override
    public List<UserSuccessBean> apiSuccessSearch(Integer page,String id){
        return baseMapper.apiSuccessSearch(page,id);
    }
}

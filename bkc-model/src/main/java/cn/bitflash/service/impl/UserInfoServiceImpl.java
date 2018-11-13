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

import cn.bitflash.bean.UserInfoBean;
import cn.bitflash.dao.UserInfoDao;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.service.UserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {
    @Override
    public List<UserInfoBean> selectUserInfoLikeCode(String code) {
        return baseMapper.selectUserInfoLikeCode(code);

    }

    @Override
    public List<UserInfoEntity> findUserList(Integer page){
        return baseMapper.findUserList(page);
    }

    @Override
    public Integer userListCount(){
        return baseMapper.userListCount();
    }

    @Override
    public Integer freezeCount(){
        return baseMapper.freezeCount();
    }
}

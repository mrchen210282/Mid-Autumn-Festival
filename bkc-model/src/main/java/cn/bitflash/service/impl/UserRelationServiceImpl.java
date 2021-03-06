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

import cn.bitflash.bean.AdminRelationBean;
import cn.bitflash.bean.UserInfoBean;
import cn.bitflash.bean.UserRelationJoinNpcAndHlbean;
import cn.bitflash.dao.UserRelationDao;
import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.entity.UserRelationEntity;
import cn.bitflash.service.UserRelationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userRelationService")
public class UserRelationServiceImpl extends ServiceImpl<UserRelationDao, UserRelationEntity> implements UserRelationService {
    @Override
    public List<UserRelationEntity> selectTreeNodes(String uid) {
        return baseMapper.selectTreeNodes(uid);
    }

    @Override
    public Boolean insertTreeNode(String pid, String uid, String code) {
        return baseMapper.insertTreeNode(pid,uid,code);
    }

    @Override
    public List<UserRelationJoinNpcAndHlbean> selectTreeNood(String f_uid) {
        return baseMapper.selectTreeNood(f_uid);
    }

    @Override
    public List<UserInfoBean> selectRelationAndMobileByCode(String code) {
        return baseMapper.selectRelationAndMobileByCode(code);
    }
    @Override
    public List<UserInvitationCodeEntity> selectUserInvitationCode(String str) {
        return baseMapper.selectUserInvitationCode(str);
    }
    @Override
    public List<UserRelationEntity> selectUserRelationCode(@Param("code") String code) {
        return baseMapper.selectUserRelationCode(code);
    }

    @Override
    public List<AdminRelationBean> findTree(){
        return baseMapper.findTree();
    }

    @Override
    public AdminRelationBean findNode(String realname){
        return baseMapper.findNode(realname);
    }

    @Override
    public List<AdminRelationBean> findCode(String fatherCode,String uid){
        return baseMapper.findCode(fatherCode,uid);
    }
}

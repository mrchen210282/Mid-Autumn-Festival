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

import cn.bitflash.dao.UserSecretDao;
import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.service.UserSecretService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userSecretService")
public class UserSecretServiceImpl extends ServiceImpl<UserSecretDao, UserSecretEntity> implements UserSecretService {

    @Override
    public String selectUid(Map<String, Object> map) {
        baseMapper.selectUid(map);
        return map.get("uid_out").toString();
    }
}

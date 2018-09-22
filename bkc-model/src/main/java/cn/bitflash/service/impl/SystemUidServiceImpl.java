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

import cn.bitflash.dao.AppStatusDao;
import cn.bitflash.dao.SystemUidDao;
import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.entity.SystemUidEntity;
import cn.bitflash.service.AppStatusService;
import cn.bitflash.service.SystemUidService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author wangjun
 * @date 2018年9月22日 下午5:06:07
 */
@Service("systemUidService")
public class SystemUidServiceImpl extends ServiceImpl<SystemUidDao, SystemUidEntity> implements SystemUidService {

    //查询用户uid
    public String selectUid() {
        String uid = baseMapper.selectUid();
        return uid;
    }
}

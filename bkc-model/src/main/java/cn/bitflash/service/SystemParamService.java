package cn.bitflash.service;


import cn.bitflash.entity.SystemParamEntity;
import cn.bitflash.entity.UserInfoEntity;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface SystemParamService extends IService<SystemParamEntity> {

    List<SystemParamEntity> apiParamList(Integer page);

    Integer paramListCount();
}

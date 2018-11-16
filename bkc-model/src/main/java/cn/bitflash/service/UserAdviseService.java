package cn.bitflash.service;

import cn.bitflash.bean.UserAdviseBean;
import cn.bitflash.entity.UserAdviseEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface UserAdviseService extends IService<UserAdviseEntity> {
    List<UserAdviseBean> adviseList(Integer page);

    Integer adviseCount();
}

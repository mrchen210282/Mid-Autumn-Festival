package cn.bitflash.service;


import cn.bitflash.bean.UserInfoBean;
import cn.bitflash.entity.UserInfoEntity;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface UserInfoService extends IService<UserInfoEntity> {

    List<UserInfoBean> selectUserInfoLikeCode(String code);

}

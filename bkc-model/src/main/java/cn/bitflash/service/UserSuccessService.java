package cn.bitflash.service;

import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.bean.UserSuccessBean;
import cn.bitflash.entity.AppStatusEntity;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface UserSuccessService extends IService<UserSuccessBean> {

    List<UserSuccessBean> selectSuccessList(String uid, Integer pages);

    Integer selectSuccessCount(String uid);

    UserSuccessBean getSuccessMessage(@Param("id") String uid, @Param("state") String state);
}

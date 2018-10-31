package cn.bitflash.service;

import cn.bitflash.bean.AdminOrderBean;
import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.bean.UserComplaintBean;
import cn.bitflash.entity.UserComplaintEntity;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface UserComplaintService extends IService<UserComplaintEntity> {

    List<UserBuyBean> selectAppealList(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);

    Integer selectAppealCount(@RequestParam("uid") String uid);

    UserComplaintBean getComplaintMessage(String id);

    int selectAppealPrompt(String uid);

    List<AdminOrderBean> apiComplaintList(Integer page);

    Integer apiComplaintCount();

    List<AdminOrderBean> apiComplaintSearch(Integer page,String id);

}

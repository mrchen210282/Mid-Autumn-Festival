package cn.bitflash.service;

import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.entity.UserSendEntity;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface UserSendService extends IService<UserSendEntity> {
    List<UserSendEntity> selectAccount(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages);

    List<UserSendEntity> selectAccept(@RequestParam("uid")String uid, @RequestParam("pages")Integer pages);

    Integer selectAccountCount(@RequestParam("uid")String uid);

    Integer selectAcceptCount(@RequestParam("uid")String send_uid);
}

package cn.bitflash.service;

import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.entity.UserMarketBuyEntity;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface UserMarketBuyService extends IService<UserMarketBuyEntity> {
    Integer showBuyingCount(String uid);

    List<UserBuyBean> showBuying(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);

    List<UserBuyBean> showOrder(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages);

    Integer showOrderCount(@RequestParam("uid") String uid);

    UserBuyBean checkOrder(@RequestParam("id")String id);

    int selectBuyPrompt(String uid);
}

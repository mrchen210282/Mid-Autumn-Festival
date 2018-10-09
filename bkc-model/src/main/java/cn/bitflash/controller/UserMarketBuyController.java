package cn.bitflash.controller;

import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.entity.UserMarketBuyEntity;
import cn.bitflash.service.UserMarketBuyService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserMarketBuyController {

    @Autowired
    private UserMarketBuyService userMarketBuyService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userMarketBuy/selectById")
    public UserMarketBuyEntity selectById(@RequestParam("id") String id) {
        UserMarketBuyEntity entity = userMarketBuyService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userMarketBuy/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserMarketBuyEntity entity = (UserMarketBuyEntity) JSONObject.parseObject(json.toString(), UserMarketBuyEntity.class);
        return userMarketBuyService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userMarketBuy/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserMarketBuyEntity entity = (UserMarketBuyEntity) JSONObject.parseObject(json.toString(), UserMarketBuyEntity.class);
        return userMarketBuyService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userMarketBuy/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userMarketBuyService.deleteById(id);
    }

    /**
     * showBuying
     *
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userMarketBuy/showBuying")
    List<UserBuyBean> showBuying(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages) {
        List<UserBuyBean> userBuyBeans = userMarketBuyService.showBuying(uid, pages);
        return userBuyBeans;
    }

    /**
     * showOrder
     *
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userMarketBuy/showOrder")
    List<UserBuyBean> showOrder(@RequestParam("uid") String uid,@RequestParam("pages") Integer pages){
        List<UserBuyBean> userBuyBeans = userMarketBuyService.showOrder(uid, pages);
        return userBuyBeans;
    }

    /**
     * showBuyingCount
     *
     * @return
     */
    @PostMapping("/inner/userMarketBuy/showBuyingCount")
    Integer showBuyingCount(@RequestParam("uid") String uid){
        int count = userMarketBuyService.showBuyingCount(uid);
        return count;
    }

    /**
     * showOrderCount
     *
     * @param uid
     * @return
     */
    @PostMapping("/inner/userMarketBuy/showOrderCount")
    Integer showOrderCount(@RequestParam("uid") String uid){
        int count = userMarketBuyService.showOrderCount(uid);
        return count;
    }

    /**
     * checkOrder
     *
     */
    @PostMapping("/inner/userMarketBuy/checkOrder")
    UserBuyBean checkOrder(@RequestParam("id")String id){
        UserBuyBean userBuyBean = userMarketBuyService.checkOrder(id);
        return userBuyBean;
    }

}

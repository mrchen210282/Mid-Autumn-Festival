package cn.bitflash.controller;

import cn.bitflash.bean.AdminOrderBean;
import cn.bitflash.bean.UserBuyBean;
import cn.bitflash.entity.*;
import cn.bitflash.service.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserMarketBuyController {

    @Autowired
    private UserMarketBuyService userMarketBuyService;

    @Autowired
    private UserAssetsNpcService userAssetsNpcService;

    @Autowired
    private BuyPoundageService buyPoundageService;

    @Autowired
    private UserComplaintService userComplaintService;

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

    @PostMapping("/inner/userMarketBuy/selectBuyPrompt")
    int selectBuyPrompt(@RequestParam("uid") String uid){
        return userMarketBuyService.selectBuyPrompt(uid);
    }

    @PostMapping("/inner/userMarketBuy/selectBuyState")
    List<UserMarketBuyEntity> selectBuyState(@RequestParam("state") String state){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("state",state);
        List<UserMarketBuyEntity> list = userMarketBuyService.selectByMap(map);
        return list;
    }


    /**
     * admin
     * apiBuyList
     */
    @GetMapping("/inner/userMarketBuy/apiBuyList/{page}")
    public List<AdminOrderBean> apiBuyList(@PathVariable String page) {
        List<AdminOrderBean> list = userMarketBuyService.apiBuyList(Integer.parseInt(page));
        return list;
    }

    /**
     * admin
     * apiBuyListCount
     */
    @GetMapping("/inner/userMarketBuy/apiBuyListCount")
    public Integer apiBuyListCount() {
        Integer count = userMarketBuyService.apiBuyListCount();
        return count;
    }

    /**
     * admin
     * apiBuySearch
     */
    @GetMapping("/inner/userMarketBuy/apiBuySearch/{page}/{id}")
    public List<AdminOrderBean> apiBuySearch(@PathVariable String page,@PathVariable String id) {
        List<AdminOrderBean> list = userMarketBuyService.apiBuySearch(Integer.parseInt(page),id);
        return list;
    }

    /**
     * showSearchCount
     *
     * @return
     */
    @GetMapping("/inner/userMarketBuy/showSearchCount/{id}")
    public Integer showSearchCount(@PathVariable String id){
        int count = userMarketBuyService.showBuyingCount(id);
        return count;
    }

    /**
     * findById
     *
     * @return
     */
    @GetMapping("/inner/userMarketBuy/findById/{id}")
    public AdminOrderBean findById(@PathVariable String id){
        AdminOrderBean order = userMarketBuyService.findById(id);
        return order;
    }

    /**
     * 申诉成功
     * complaintSuccess
     */
    @GetMapping("/inner/userMarketBuy/complaintSuccess/{id}")
    public void complaintSuccess(@PathVariable String id){
        UserMarketBuyEntity entity = userMarketBuyService.selectById(id);
        if("".equals(entity.getPayTime()) || entity.getPayTime() == null){
            entity.setState("2");
        }else {
            entity.setState("3");
        }
        userMarketBuyService.updateById(entity);

        UserComplaintEntity complaint = userComplaintService.selectById(id);
        complaint.setOrderState("1");
        userComplaintService.updateById(complaint);
    }

    /**
     * 申诉失败
     * complaintFail
     */
    @GetMapping("/inner/userMarketBuy/complaintFail/{id}")
    public void complaintFail(@PathVariable String id){
        //获取订单信息
        UserMarketBuyEntity entity = userMarketBuyService.selectById(id);

        //接单人返还npc
        UserAssetsNpcEntity userAssetsNpcEntity = userAssetsNpcService.selectById(entity.getSellUid());
        userAssetsNpcEntity.setAvailableAssets(userAssetsNpcEntity.getAvailableAssets()+entity.getQuantity());
        userAssetsNpcService.updateById(userAssetsNpcEntity);

        //删除订单
        userMarketBuyService.deleteById(id);

        //删除手续费
        buyPoundageService.deleteById(id);

        //申诉订单状态变为1
        UserComplaintEntity complaint = userComplaintService.selectById(id);
        complaint.setOrderState("1");
        userComplaintService.updateById(complaint);
    }

}

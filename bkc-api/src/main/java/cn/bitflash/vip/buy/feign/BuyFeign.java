package cn.bitflash.vip.buy.feign;

import cn.bitflash.entities.*;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserBuyBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface BuyFeign {

//-------------------------------------------------------usreBuy--------------------------------------------------------

    /**
     * insert
     *
     * @param entity
     */
    @PostMapping("/inner/userBuy/updateById")
    void insertBuy(@RequestBody UserBuyEntity entity);

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/userBuy/selectById")
    UserBuyEntity selectBuyById(@RequestParam("id") String id);

    /**
     * updateById
     *
     * @param entity
     */
    @PostMapping("/inner/userBuy/updateById")
    void updateBuyById(@RequestBody UserBuyEntity entity);

    /**
     * deleteById
     *
     * @param id
     */
    @PostMapping("/inner/userBuy/deleteById")
    void deleteBuyById(@RequestParam("id") String id);

    /**
     * showBuying
     *
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userBuy/showBuying")
    List<UserBuyBean> showBuying(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);


    /**
     * showBuyingCount
     *
     * @return
     */
    @PostMapping("/inner/userBuy/showBuyingCount")
    Integer showBuyingCount(@RequestParam("uid") String uid);

    /**
     * showOrder
     *
     * @param uid
     * @param pages
     * @return
     */
    @PostMapping("/inner/userBuy/showOrder")
    List<UserBuyBean> showOrder(@RequestParam("uid") String uid, @RequestParam("pages") Integer pages);


    /**
     * showOrderCount
     *
     * @param uid
     * @return
     */
    @PostMapping("/inner/userBuy/showOrderCount")
    Integer showOrderCount(@RequestParam("uid") String uid);


    /**
     * checkOrder
     */
    @PostMapping("/inner/userBuy/checkOrder")
    UserBuyBean checkOrder(@RequestParam("id") String id);


//-------------------------------------------------------userBuyHistory-------------------------------------------------

    /**
     * insert
     *
     * @param entity
     */
    @PostMapping("/inner/userBuyHistory/insert")
    void insertHistory(@RequestBody UserBuyHistoryEntity entity);

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/userBuyHistory/selectById")
    UserBuyHistoryEntity selectHistoryById(@RequestParam("id") String id);

    /**
     * updateById
     *
     * @param entity
     */
    @PostMapping("/inner/userBuyHistory/updateById")
    void updateHistoryById(@RequestBody UserBuyHistoryEntity entity);

    /**
     * deleteById
     *
     * @param id
     */
    @PostMapping("/inner/userBuyHistory/deleteById")
    void deleteHistoryById(@RequestParam("id") String id);


//-------------------------------------------------------userComplaint-------------------------------------------------

    /**
     * insert
     *
     * @param entity
     */
    @PostMapping("/inner/userComplaint/insert")
    void insertComplaint(@RequestBody UserComplaintEntity entity);


//-------------------------------------------------------buyPoundage-------------------------------------------------

    /**
     * insert
     *
     * @param entity
     */
    @PostMapping("/inner/buyPoundage/insert")
    void insertPoundage(@RequestBody BuyPoundageEntity entity);

    /**
     * deletePoundage
     */
    @PostMapping("/inner/buyPoundage/deleteById")
    void deletePoundage(@RequestParam("id") String id);

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/buyPoundage/selectById")
    BuyPoundageEntity selectPoundageById(@RequestParam("id") String id);


//-------------------------------------------------------userAccount-------------------------------------------------

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/userAccount/selectById")
    UserAccountEntity selectAccountById(@RequestParam("id") String id);

    /**
     * updateAccountById
     *
     * @param entity
     */
    @PostMapping("/inner/userAccount/updateById")
    void updateAccountById(@RequestBody UserAccountEntity entity);


//-------------------------------------------------------userBrokerage-------------------------------------------------

    /**
     * updateById
     *
     * @param entity
     */
    @PostMapping("/inner/userBrokerage/updateById")
    void updateBrokerageById(@RequestBody UserBrokerageEntity entity);

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/userBrokerage/selectById")
    UserBrokerageEntity selectBrokerageById(@RequestParam("id") int id);


//-------------------------------------------------------FINISH-------------------------------------------------

    /**
     * selectUid
     *
     * @param uid
     * @return
     */
    @PostMapping("/inner/userPayPwd/selectById")
    UserPayPwdEntity selectUid(@RequestParam("id") String uid);

    /**
     * getVal
     *
     * @param key
     * @return
     */
    @PostMapping("/inner/platFormConfig/getVal")
    String getVal(@RequestParam("key") String key);

    /**
     * selectCid
     *
     * @return
     */
    @PostMapping("/inner/userGTCidEntity/selectOne")
    String selectCid(@RequestParam("uid") String uid);


    /**
     * selectPoundage
     */
    @PostMapping("/inner/userTradeConfig/selectById")
    Float selectPoundage(@RequestParam("id") int id);
}

package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.*;
import cn.bitflash.vip.buy.Bean.UserBuyBean;
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
    void insertBuy(@RequestBody UserMarketBuyEntity entity);

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/userBuy/selectById")
    UserMarketBuyEntity selectBuyById(@RequestParam("id") String id);

    /**
     * updateById
     *
     * @param entity
     */
    @PostMapping("/inner/userBuy/updateById")
    void updateBuyById(@RequestBody UserMarketBuyEntity entity);

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
    void insertHistory(@RequestBody UserMarketBuyHistoryEntity entity);

    /**
     * selectById
     *
     * @param id
     * @return
     */
    @PostMapping("/inner/userBuyHistory/selectById")
    UserMarketBuyHistoryEntity selectHistoryById(@RequestParam("id") String id);

    /**
     * updateById
     *
     * @param entity
     */
    @PostMapping("/inner/userBuyHistory/updateById")
    void updateHistoryById(@RequestBody UserMarketBuyHistoryEntity entity);

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
    UserAssetsNpcEntity selectAccountById(@RequestParam("id") String id);

    /**
     * updateAccountById
     *
     * @param entity
     */
    @PostMapping("/inner/userAccount/updateById")
    void updateAccountById(@RequestBody UserAssetsNpcEntity entity);


//-------------------------------------------------------userBrokerage-------------------------------------------------
//
//    /**
//     * updateById
//     *
//     * @param entity
//     */
//    @PostMapping("/inner/userBrokerage/updateById")
//    void updateBrokerageById(@RequestBody UserBrokerageEntity entity);
//
//    /**
//     * selectById
//     *
//     * @param id
//     * @return
//     */
//    @PostMapping("/inner/userBrokerage/selectById")
//    UserBrokerageEntity selectBrokerageById(@RequestParam("id") int id);


//-------------------------------------------------------FINISH-------------------------------------------------

    /**
     * selectUid
     *
     * @param uid
     * @return
     */
    @PostMapping("/inner/userPayPwd/selectById")
    UserSecretEntity selectUid(@RequestParam("id") String uid);

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

package cn.bitflash.vip.user.feign;

import cn.bitflash.entity.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserFeign {

    /**
     * tb_user表
     */
    UserEntity selectUserByUid(@RequestParam("uid")String uid);

    Boolean updateUserById(@RequestBody UserEntity userEntity);

    /**
     * user_account表
     */
    UserAccountEntity selectAccountByUid(@RequestParam("uid")String uid);

    /**
     * user_info表
     */
    //返回对应列的值
    String selectUserInfoByColumn(@RequestParam("uid")String uid, @RequestParam("column")String column);

    Boolean updateUserInfoById(@RequestBody UserInfoEntity userInfo);

    /**
     * user_trade_hostory表
     */
    //finsh_time !=null
    List<UserTradeHistoryEntity> selectTradeHistoryList(@RequestParam("uid")String uid);

    /**
     * user_buy_hostory表
     */
    List<UserBuyHistoryEntity> selectBuyHistoryList(@RequestParam("uid")String uid);

    /**
     * user_pay_url表
     */
    UserPayUrlEntity selectUserPayUrlByUidAndType(@RequestParam("uid")String uid, @RequestParam("type")String type);

    Boolean insertUserUrl(@RequestBody UserPayUrlEntity userPayUrlEntity);

    Boolean updateUserUrlById(@RequestBody UserPayUrlEntity userPayUrlEntity);

    //imgType 1,2,5
    List<UserPayUrlEntity> selectUserUrlList(@RequestParam("uid")String uid);

    /**
     * user_pay_pwd 表
     */
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("uid")String uid);

    Boolean insertUserPwd(@RequestBody UserPayPwdEntity userPayPwdEntity);

    Boolean updateUserPwdById(@RequestBody UserPayPwdEntity userPayPwdEntity);

    /**
     * user_trade 表
     */
    UserTradeEntity selectTradeById(@RequestParam("id")String id);

    /**
     * user_buy_history 表
     */
    UserBuyHistoryEntity selectBuyHistoryById(@RequestParam("id")String id);
}

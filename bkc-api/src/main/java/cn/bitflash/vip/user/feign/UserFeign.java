package cn.bitflash.vip.user.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "bkc-model")
public interface UserFeign {

    /**
     * tb_user表
     */
    @PostMapping("/inner/user/selectById")
    UserEntity selectUserByUid(@RequestParam("id") String id);

    @PostMapping("/inner/user/updateById")
    Boolean updateUserById(@RequestBody UserEntity userEntity);

    /**
     * user_account表
     */
    @PostMapping("/inner/userDigitalIncome/selectById")
    UserDigitalIncomeEntity selectAccountByUid(@RequestParam("id") String id);

    /**
     * user_info表
     */
    //返回对应列的值
    @PostMapping("")
    String selectUserInfoByColumn(@RequestParam("uid") String uid, @RequestParam("column") String column);

    @PostMapping("/inner/userInfo/updateById")
    Boolean updateUserInfoById(@RequestBody UserInfoEntity userInfo);

    @PostMapping("/inner/userInfo/selectById")
    UserInfoEntity selectUserinfoById(@RequestParam("id") String id);

    /**
     * user_pay_url表
     */
    @PostMapping("")
    UserPayImgEntity selectUserPayUrlByUidAndType(@RequestParam("uid") String uid, @RequestParam("type") String type);

    @PostMapping("/inner/userPayUrl/insert")
    Boolean insertUserUrl(@RequestBody UserPayImgEntity userPayUrlEntity);

    @PostMapping("/inner/userPayUrl/updateById")
    Boolean updateUserUrlById(@RequestBody UserPayImgEntity userPayUrlEntity);

    //imgType 1,2,5
    @PostMapping("")
    List<UserPayImgEntity> selectUserUrlList(@RequestParam("uid") String uid);

    /**
     * user_pay_pwd 表
     */
    @PostMapping("/inner/userPayPwd/selectById")
    UserPayPwdEntity selectUserPwdByUid(@RequestParam("id") String id);

    @PostMapping("/inner/userPayPwd/insert")
    Boolean insertUserPwd(@RequestBody UserPayPwdEntity userPayPwdEntity);

    @PostMapping("/inner/userPayPwd/updateById")
    Boolean updateUserPwdById(@RequestBody UserPayPwdEntity userPayPwdEntity);
}

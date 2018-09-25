package cn.bitflash.vip.index.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bkc-model")
public interface IndexFeign {

    /**
     * user_login表
     */

    /**
     * 根据手机号查询用户
     * @param mobile
     * @return
     */
    @PostMapping("/inner/userLogin/selectByMobile")
    UserLoginEntity selectUserLoginEntityByMobile(@RequestParam("id")String mobile);

    /**
     * 插入
     * @param userEntity
     * @return
     */
    @PostMapping("/inner/userLogin/insert")
    String insertUserLoginEntity(@RequestBody UserLoginEntity userEntity);

    /**
     * 更新
     * @param userEntity
     * @return
     */
    @PostMapping("/inner/userLogin/updateById")
    void updateUserLoginById(@RequestBody UserLoginEntity userEntity);


    /**
     * user_info表
     */

    /**
     * 更新
     * @param infoEntity
     * @return
     */
    @PostMapping("/inner/userInfo/updateById")
    void updateUserInfoById(@RequestBody UserInfoEntity infoEntity);
}

package cn.bitflash.vip.index.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
    UserLoginEntity selectUserLoginEntityByMobile(@RequestParam("mobile")String mobile);

    /**
     * 插入
     * @param userEntity
     * @return
     */
    @PostMapping("/inner/userLogin/registerLogin")
    String registerLogin(@RequestBody UserLoginEntity userEntity);

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
    Boolean updateUserInfoById(@RequestBody UserInfoEntity infoEntity);

    /**
     * 取得首页资产
     */
    @PostMapping("/inner/assets/getIndexAssets")
    Map<String,Object> getIndexAssets(@RequestParam("uid") String uid);
}

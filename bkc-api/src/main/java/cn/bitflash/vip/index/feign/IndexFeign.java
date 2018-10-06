package cn.bitflash.vip.index.feign;

import cn.bitflash.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@FeignClient(value = "bkc-model")
public interface IndexFeign {

    /**
     * user_login表
     */
    //根据手机号查询用户
    @PostMapping("/inner/userLogin/selectByMobile")
    UserSecretEntity selectUserLoginEntityByMobile(@RequestParam("mobile")String mobile);

    @PostMapping("/inner/userLogin/registerLogin")
    String registerLogin(@RequestBody UserSecretEntity userEntity);

    @PostMapping("/inner/userLogin/updateById")
    void updateUserLoginById(@RequestBody UserSecretEntity userEntity);

    /**
     * user_info表
     */
    @PostMapping("/inner/userInfo/updateById")
    Boolean updateUserInfoById(@RequestBody UserInfoEntity infoEntity);

    @PostMapping
    Boolean insertUserInfoById(@RequestBody UserInfoEntity infoEntity);

    /**
     * SystemAppBanner 表
     */
    @PostMapping("/inner/systemAppBanner/selectAppBanner")
    List<SystemAppBannerEntity> selectAppBanner();

    /**
     * price_line_chart 表
     */
    @PostMapping("/inner/priceLinechart/selectPriceLinechart")
    PriceLinechartEntity selectPriceLinechart();

    //查询昨天的汇率
    @PostMapping("/inner/priceLinechart/selectLineChartYesterDayByDate")
    List<PriceLinechartEntity> selectLineChartYesterDayByDate(@RequestParam("yesterday") String yesterday);

    @PostMapping("/inner/priceLinechart/selectPriceUs")
    PriceLinechartEntity selectPriceUs();

    @PostMapping("/inner/priceLinechart/selectPriceCny")
    PriceLinechartEntity selectPriceCny();

    /**
     * user_invitation_code 表
     */
    @PostMapping("/inner/userInvitationCode/selectInvitationCodeByCode")
    UserInvitationCodeEntity selectInvitationCodeByCode(@RequestParam("code") String code);

    /**
     * user_getui 表
     */
    @PostMapping
    Boolean insertOrupdateGetui(@RequestBody UserGetuiEntity getui);


}

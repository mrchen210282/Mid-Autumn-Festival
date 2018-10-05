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
     * insert
     *
     * @return
     */
    @PostMapping("/inner/systemAdvertisement/insertSystemAdvertisement")
    Boolean insertSystemAdvertisement(@RequestParam("img")String img);

    /**
     * 查询轮播图片
     * @return
     */
    @PostMapping("/inner/systemAppBanner/selectAppBanner")
    List<SystemAppBannerEntity> selectAppBanner();

    /**
     * 首页贝壳信息
     * @return
     */
    @PostMapping("/inner/priceLinechart/selectPriceLinechart")
    public PriceLinechartEntity selectPriceLinechart();

    /**
     * 查询昨天的汇率
     * selectLineChartYesterDayByDate
     * @param yesterday
     * @return
     */
    @PostMapping("/inner/priceLinechart/selectLineChartYesterDayByDate")
    public List<PriceLinechartEntity> selectLineChartYesterDayByDate(@RequestParam("yesterday") String yesterday);

    @PostMapping("/inner/priceLinechart/selectPriceUs")
    public PriceLinechartEntity selectPriceUs();

    @PostMapping("/inner/priceLinechart/selectPriceCny")
    public PriceLinechartEntity selectPriceCny();

    @PostMapping("/inner/userInvitationCode/selectInvitationCodeByCode")
    UserInvitationCodeEntity selectInvitationCodeByCode(@RequestParam("code") String code);
}

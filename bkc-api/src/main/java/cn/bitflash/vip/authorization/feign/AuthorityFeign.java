package cn.bitflash.vip.authorization.feign;

import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.vip.authorization.entity.AuthorityUserEntity;
import cn.bitflash.vip.authorization.entity.GameAccountHistoryEntity;
import cn.bitflash.vip.authorization.entity.UserEmpowerEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bkc-model")
public interface AuthorityFeign {

    /**
     * selectUserEmpowerById
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/selectUserEmpowerById")
    UserEmpowerEntity selectUserEmpowerById(@RequestParam("clientid") String clientid, @RequestParam("ticket") String ticket);




    /**
     * selectUserEmpowerByAppid
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/selectUserEmpowerByAppid")
    UserEmpowerEntity selectUserEmpowerByAppid(@RequestParam("clientid") String clientid);


    /**
     * selectAuthorityByMobile
     *
     * @return
     */
    @PostMapping("/inner/authority/selectAuthorityByMobile")
    AuthorityUserEntity selectAuthorityByMobile(@RequestParam("mobile") String mobile);

    @PostMapping("/inner/authority/selectAuthorityByUid")
    AuthorityUserEntity selectAuthorityByUid(@RequestParam("uid") String uid);

    @PostMapping("/inner/authority/insertAuthority")
    void insertAuthority(@RequestBody AuthorityUserEntity authorityUserEntity);

    /**
     * selectAuthorityById
     *
     * @return
     */
    @PostMapping("/inner/authority/selectAuthorityById")
    AuthorityUserEntity selectAuthorityById(@RequestParam("id") String id);

    @PostMapping("/inner/userAssetsNpc/selectById")
    UserAssetsNpcEntity selectUserAssetsNpcById(@RequestParam("id")String uid);

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userInfo/selectById")
    UserInfoEntity selectById(@RequestParam("id") String id);

    /**
     * selectByMobile
     *
     * @return
     */
    @PostMapping("/inner/userInfo/selectUserInfoByMobile")
    UserInfoEntity selectUserInfoByMobile(@RequestParam("mobile") String mobile);

    /**
     * selectByMobile
     *
     * @return
     */
    @PostMapping("/inner/userLogin/selectByMobile")
    UserSecretEntity selectByMobile(@RequestParam("mobile") String mobile);

    @PostMapping("/inner/userAssetsNpc/update")
    public Boolean updateUserAssetsNpc(@RequestBody UserAssetsNpcEntity userAssetsNpcEntity);

    /**
     * insertGameAccount
     *
     * @return
     */
    @PostMapping("/inner/gameAccountHistory/insertGameAccount")
    public void insertGameAccount(@RequestBody GameAccountHistoryEntity gameAccountHistoryEntity);




}

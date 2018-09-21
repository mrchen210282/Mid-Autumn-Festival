package cn.bitflash.vip.oauth2.feign;

import cn.bitflash.entity.UserAccountEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface Oauth2Feign {

    /**
     * user_account 表
     */
    UserAccountEntity selectAccountByUid(@RequestParam("uid")String uid);

    Boolean updateAccountById(@RequestBody UserAccountEntity account);

}

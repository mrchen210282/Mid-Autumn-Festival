package cn.bitflash.controller;

import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.entity.AuthorityUserEntity;
import cn.bitflash.service.AppStatusService;
import cn.bitflash.service.AuthorityUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class AuthorityUserController {

    @Autowired
    private AuthorityUserService authorityUserService;

    /**
     * selectAuthorityByMobile
     *
     * @return
     */
    @PostMapping("/inner/authority/selectAuthorityByMobile")
    public AuthorityUserEntity selectAuthorityByMobile(@RequestParam("mobile") String mobile) {
        AuthorityUserEntity entity = authorityUserService.selectOne(new EntityWrapper<AuthorityUserEntity>().eq("mobile",mobile));
        return entity;
    }
}

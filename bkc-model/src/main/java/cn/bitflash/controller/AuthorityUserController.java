package cn.bitflash.controller;

import cn.bitflash.entity.AuthorityUserEntity;
import cn.bitflash.service.AuthorityUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    /**
     * selectAuthorityById
     *
     * @return
     */
    @PostMapping("/inner/authority/selectAuthorityById")
    public AuthorityUserEntity selectAuthorityById(@RequestParam("id") String id) {
        AuthorityUserEntity entity = authorityUserService.selectById(id);
        return entity;
    }

    @PostMapping("/inner/authority/selectAuthorityByUid")
    public AuthorityUserEntity selectAuthorityByUid(@RequestParam("uid") String uid) {
        AuthorityUserEntity authorityUser = authorityUserService.selectOne(new EntityWrapper<AuthorityUserEntity>().eq("uid", uid));
        return authorityUser;
    }

    @PostMapping("/inner/authority/insertAuthority")
    public void insertAuthority(@RequestBody JSONObject json) {
        AuthorityUserEntity authorityUser = (AuthorityUserEntity) JSONObject.parseObject(json.toString(), AuthorityUserEntity.class);
        authorityUserService.insert(authorityUser);
    }

}

package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserAdviseEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("advise")
public class Advise {

    @Autowired
    private UserFeign userFeign;

    @Login
    @GetMapping("addAdvise")
    public R addAdvise(@RequestParam String  advise,@RequestAttribute("uid")String uid){
        if(advise.length()>200 || advise.length()<=0){
            return R.error("字符长度有误,请重新输入");
        }
        UserAdviseEntity adviseEntity = new UserAdviseEntity();
        adviseEntity.setCreateTime(new Date());
        adviseEntity.setUid(uid);
        adviseEntity.setAdvise(advise);
        userFeign.insertUserAdvise(adviseEntity);
        return R.ok();
    }
}

package cn.bitflash.controller;

import cn.bitflash.entity.UserGTCidEntity;
import cn.bitflash.service.UserGTCidService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserGTCidController {

    @Autowired
    private UserGTCidService userGTCidService;


    /**
     * selectCid
     *
     * @return
     */
    @PostMapping("/inner/userGTCidEntity/selectCid")
    String selectCid(@RequestParam("uid") String uid){
        String cid = userGTCidService.selectOne(new EntityWrapper<UserGTCidEntity>().eq("uid",uid)).getCid();
        return cid;
    }

    /**
     * user_getui_cid表
     */
    @PostMapping("/inner/userGTCidEntity/insertOrUpdateGT")
    public Boolean insertOrUpdateGT(@RequestBody UserGTCidEntity userGTCidEntity){
        return userGTCidService.insertOrUpdate(userGTCidEntity);
    }
}

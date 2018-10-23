package cn.bitflash.controller;

import cn.bitflash.entity.UserComplaintEntity;
import cn.bitflash.entity.UserEmpowerEntity;
import cn.bitflash.service.UserComplaintService;
import cn.bitflash.service.UserEmpowerService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * wangjun
 * 2018.10.17
 */
@RestController
public class UserEmpowerController {

    @Autowired
    private UserEmpowerService userEmpowerService;

    /**
     * selectUserEmpowerById
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/selectUserEmpowerById")
    public UserEmpowerEntity selectUserEmpowerById(@RequestParam("clientid") String clientid,@RequestParam("ticket") String ticket) {
        UserEmpowerEntity entity = userEmpowerService.selectOne(new EntityWrapper<UserEmpowerEntity>().eq("appid",clientid).eq("ticket",ticket));
        return entity;
    }

    /**
     * selectUserEmpowerByAppid
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/selectUserEmpowerByAppid")
    public UserEmpowerEntity selectUserEmpowerByAppid(@RequestParam("clientid") String clientid) {
        UserEmpowerEntity entity = userEmpowerService.selectOne(new EntityWrapper<UserEmpowerEntity>().eq("appid",clientid));
        return entity;
    }
}

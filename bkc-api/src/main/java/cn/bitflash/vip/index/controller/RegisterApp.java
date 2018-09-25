package cn.bitflash.vip.index.controller;

import cn.bitflash.entity.UserLoginEntity;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.utils.Encrypt;
import cn.bitflash.utils.R;
import cn.bitflash.utils.RandomNumUtil;
import cn.bitflash.vip.index.feign.IndexFeign;
import cn.bitflash.vip.user.controller.WalletAddress;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/index")
@Api(value = "注册Con", tags = {"用户app注册"})
public class RegisterApp {

    @Autowired
    private IndexFeign indexFeign;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("registerWeb")
    public R register2(@RequestParam String mobile, @RequestParam String pwd,
                       @RequestParam("invitationCode") String invitationCode, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        UserLoginEntity oldUser = indexFeign.selectUserLoginEntityByMobile(mobile);
        if (oldUser != null) {
            return R.error(501, "手机号已经存在");
        }

        String salt = RandomNumUtil.nBit(4);
        UserLoginEntity us = new UserLoginEntity();
        us.setMobile(mobile);
        String finalPwd = Encrypt.SHA512(pwd+salt);
        us.setPassword(finalPwd);
        us.setSalt(salt);
        //初始化user_login表
        String uid = indexFeign.registerLogin(us);
        UserInfoEntity info = new UserInfoEntity();
        info.setUid(uid);
        info.setInvitationCode(invitationCode);
        info.setIsInvitated("Y");
        Boolean flag2 = indexFeign.updateUserInfoById(info);
        WalletAddress walletAddress = new WalletAddress();
        try {
            boolean stu = walletAddress.createWalletAddress(uid);
            if(!stu){
                return R.error("注册失败");
            }
        }catch (Exception e){
            return R.error("注册失败");
        }
        if(!flag2){
            return R.error("注册失败");
        }


        logger.info("注册手机号:"+mobile+",邀请码："+invitationCode);
        return R.ok("注册成功");
    }


}

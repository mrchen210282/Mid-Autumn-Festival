package cn.bitflash.vip.index.controller;

import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.utils.Encrypt;
import cn.bitflash.utils.R;
import cn.bitflash.vip.index.feign.IndexFeign;
import cn.bitflash.vip.user.controller.WalletAddress;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.RandomStringUtils;
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
    public R registerWeb(@RequestParam String mobile, @RequestParam String pwd,
                         @RequestParam("invitationCode") String invitationCode, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        UserSecretEntity oldUser = indexFeign.selectUserLoginEntityByMobile(mobile);
        if (oldUser != null) {
            return R.error(501, "手机号已经存在");
        }
        String code[] = invitationCode.split("-");
        String invitCode = code[0];
        String area = code[1];
        UserInvitationCodeEntity pCode = indexFeign.selectInvitationCodeByCode(invitCode);
        logger.info("邀请码：------"+invitationCode+",--------------左右区："+area);
        if (pCode == null) {
            return R.error("邀请码错误！");
        }
        if(area.equals("R") || area.equals("L")){
            UserSecretEntity us = new UserSecretEntity();
            us.setMobile(mobile);
            String salt = RandomStringUtils.randomAlphanumeric(16);
            String finalPwd = Encrypt.SHA256(pwd + salt);
            us.setPassword(finalPwd);
            us.setSalt(salt);
            //初始化user_login表
            String uid = indexFeign.registerLogin(us);
            UserInfoEntity info = new UserInfoEntity();
            info.setRealname(uid);
            info.setUid(uid);
            info.setInvitationCode(invitCode);
            info.setIsInvited("Y");
            info.setArea(area);
            Boolean flag2 = indexFeign.insertUserInfoById(info);
            //创建钱包地址
            WalletAddress walletAddress = new WalletAddress();
            try {
                walletAddress.createWalletAddress(uid);
            } catch (Exception e) {
                return R.error("注册失败");
            }
            if (!flag2) {
                return R.error("注册失败");
            }

            logger.info("注册手机号:" + mobile + ",邀请码：" + invitationCode);
            return R.ok("注册成功");
        }else{
            return R.error("分区邀请码错误");
        }

    }

}

package cn.bitflash.vip.index.controller;

import cn.bitflash.entity.UserEntity;
import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.util.R;
import cn.bitflash.util.ValidatorUtils;
import cn.bitflash.vip.index.entity.RegisterForm;
import cn.bitflash.vip.index.feign.IndexFeign;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/index")
@Api(value = "注册Con", tags = {"用户app注册"})
public class RegisterApp {

    @Autowired
    private IndexFeign indexFeign;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("register")
    public R register(@RequestBody RegisterForm form) {
        //验证表单
        ValidatorUtils.validateEntity(form);
        String mobile = form.getMobile();
        UserEntity oldUser = indexFeign.selectUserEntityByMobile(mobile);
        if (oldUser != null) {
            return R.error(501, "手机号已经存在");
        }

        String uid = generateUUID32();
        Boolean flag = indexFeign.insertUserEntity(uid, mobile, form.getPwd(), generateUUID32());
        if (flag) {
            Date now = new Date();
            Boolean flag2 = indexFeign.insertAccount(uid, now);
            if (flag2) {
                String name = this.getName();
                Boolean flag3 = indexFeign.insertInfo(uid, mobile, false, name);
                if (flag3) {
                    logger.info("手机号：" + form.getMobile() + ",注册成功，途径app，没有推广码");
                    return R.ok("注册成功");

                }
            }
        }
        indexFeign.delUserEntityBymMbile(mobile);
        indexFeign.delAccountByUid(uid);
        indexFeign.delGameByUid(uid);
        indexFeign.delUserInfoByUid(uid);

        return R.error("注册失败");
    }

    @GetMapping("registerWeb")
    public R register2(@RequestParam String mobile, @RequestParam String pwd,
                       @RequestParam("invitationCode") String invitationCode, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        UserEntity oldUser = indexFeign.selectUserEntityByMobile(mobile);
        if (oldUser != null) {
            return R.error(501, "手机号已经存在");
        }

        String uid = generateUUID32();
        Boolean flag = indexFeign.insertUserEntity(uid, mobile, pwd, generateUUID32());
        if (flag) {
            Date now = new Date();
            Boolean flag2 = indexFeign.insertAccount(uid, now);
            if (flag2) {
                Boolean flag3 = indexFeign.insertGame(uid, now);
                if (flag3) {
                    // 校验验证码是否正确
                    UserInvitationCodeEntity userInvitationCodeEntity = indexFeign.selectCodeByCode(invitationCode);
                    if (userInvitationCodeEntity == null) {
                        String name = this.getName();
                        Boolean flag4 = indexFeign.insertInfoCode(uid, mobile, true, name, invitationCode);
                        if (flag4) {
                            logger.info("手机号：" + mobile + ",注册成功,邀请码：" + invitationCode);
                            return R.ok("注册成功");
                        }
                    }

                }
            }
        }
        indexFeign.delUserEntityBymMbile(mobile);
        indexFeign.delAccountByUid(uid);
        indexFeign.delGameByUid(uid);
        indexFeign.delUserInfoByUid(uid);

        return R.error("注册失败");
    }

    private String generateUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public String getName() {
        String str = "用户";
        int max = 10000;
        int min = 1000;
        Random random = new Random();
        str += random.nextInt(max) % (max - min + 1) + min;
        return str;
    }
}

package cn.bitflash.vip.system.controller;

import cn.bitflash.entity.UserEntity;
import cn.bitflash.util.R;
import cn.bitflash.util.SmsUtils;
import cn.bitflash.vip.system.feign.SystemFeign;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RestController
@RequestMapping("/system")
@Api(value = "短息Con",tags={"注册时发送短信验证"})
public class VerifyCode {

    @Autowired
    private SystemFeign systemFeign;

    @RequestMapping("getVerifyCode")
    public R sent(@RequestParam String mobile, @RequestParam String type, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        if (StringUtils.isBlank(mobile)) {
            return R.error(501, "手机号不能为空");
        }
        UserEntity userEntity = systemFeign.selectUserEntityByMobile(mobile);
        String verifyCode = generateCode();
        if (type.equals("reg")) {
            if (userEntity != null) {
                return R.error("用户已注册，请直接登录");
            }
            return SmsUtils.smsApi(mobile, verifyCode, "贝壳", "SMS_135330146");
        }
        // 找回密码
        if (type.equals("findPwd")) {
            if (userEntity == null) {
                return R.error("手机号错误");
            }
            return SmsUtils.smsApi(mobile, verifyCode, "贝壳", "SMS_136065023");
        }
        return R.error("系统错误");
    }

    private String generateCode() {
        return String.valueOf(new Random().nextInt(8999) + 1000);
    }
}

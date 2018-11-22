package cn.bitflash.vip.system.controller;

import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.utils.R;
import cn.bitflash.utils.SmsUtils;
import cn.bitflash.vip.system.feign.SystemFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RestController
@RequestMapping("/system")
@Api(value = "短息Con", tags = {"注册时发送短信验证"})
public class VerifyCode {

    @Autowired
    private SystemFeign systemFeign;

    @RequestMapping("getVerifyCode")
    @ApiOperation("获取短信验证码")
    public R sent(@ApiParam @RequestParam String mobile, @ApiParam(value = "reg 注册验证码 findPwd 找回密码") @RequestParam String type, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        if (StringUtils.isBlank(mobile)) {
            return R.error(501, "手机号不能为空");
        }
        UserSecretEntity userEntity = systemFeign.selectUserEntityByMobile(mobile);
        String verifyCode =  RandomStringUtils.randomNumeric(6);
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
        //提现功能
        if (type.equals("cashing")) {
            if (userEntity == null) {
                return R.error("手机号不存在用户");
            }
            return SmsUtils.smsApi(mobile, verifyCode, "贝壳", "SMS_148591397");
        }
        //钱包地址
        if (type.equals("walletAdress")) {
            if (userEntity == null) {
                return R.error("手机号不存在用户");
            }
            return SmsUtils.smsApi(mobile,verifyCode, "贝壳", "SMS_146611072");
        }
        return R.error("系统错误");
    }

    @RequestMapping("getForumCode")
    public R getForumCode(@RequestParam String mobile,HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        String verifyCode = RandomStringUtils.randomNumeric(6);
        return SmsUtils.smsApi(mobile, verifyCode, "贝壳", "SMS_135330146");

    }
}

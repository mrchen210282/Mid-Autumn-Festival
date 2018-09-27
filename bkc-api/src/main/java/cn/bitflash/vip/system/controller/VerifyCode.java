package cn.bitflash.vip.system.controller;

import cn.bitflash.entity.UserLoginEntity;
import cn.bitflash.utils.R;
import cn.bitflash.utils.SmsUtils;
import cn.bitflash.vip.system.feign.SystemFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RestController
@RequestMapping("/system")
@Api(value = "��ϢCon",tags={"ע��ʱ���Ͷ�����֤"})
public class VerifyCode {

    @Autowired
    private SystemFeign systemFeign;

    @RequestMapping("getVerifyCode")
    @ApiOperation("��ȡ������֤��")
    public R sent(@ApiParam  @RequestParam String mobile,@ApiParam(value = "reg ע����֤�� findPwd �һ�����") @RequestParam String type, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        if (StringUtils.isBlank(mobile)) {
            return R.error(501, "�ֻ��Ų���Ϊ��");
        }
        UserLoginEntity userEntity = systemFeign.selectUserEntityByMobile(mobile);
        String verifyCode = generateCode();
        if (type.equals("reg")) {
            if (userEntity != null) {
                return R.error("�û���ע�ᣬ��ֱ�ӵ�¼");
            }
            return SmsUtils.smsApi(mobile, verifyCode, "����", "SMS_135330146");
        }
        // �һ�����
        if (type.equals("findPwd")) {
            if (userEntity == null) {
                return R.error("�ֻ��Ŵ���");
            }
            return SmsUtils.smsApi(mobile, verifyCode, "����", "SMS_136065023");
        }
        //���ֹ���
        if(type.equals("cashing")){
            if(userEntity == null){
                return R.error("�ֻ��Ų������û�");
            }
            return SmsUtils.smsApi(mobile, verifyCode, "����", "SMS_136065023");
        }
        //Ǯ����ַ
        if(type.equals("walletAdress")){
            if(userEntity == null){
                return R.error("�ֻ��Ų������û�");
            }
            return SmsUtils.smsApi(mobile, verifyCode, "����", "");
        }
        return R.error("ϵͳ����");
    }

    private String generateCode() {
        return String.valueOf(new Random().nextInt(8999) + 1000);
    }
}

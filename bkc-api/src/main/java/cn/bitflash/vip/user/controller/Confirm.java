package cn.bitflash.vip.user.controller;

import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.UserFeign;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "ʵ��Con", tags = {"�û��ϴ�sfzͼƬ"})
public class Confirm {

    @Autowired
    private UserFeign userFeign;

    //@Login  @RequestAttribute("uid") String uid
    @PostMapping("authentication")
    @ApiOperation("ʵ����֤")
    public R uploadImgMessage(@ApiParam @RequestParam String realname, @ApiParam @RequestParam String idnum
                             ) throws Exception {

       /* UserInfoEntity info = userFeign.selectUserinfoById(uid);
        if (info.getIsAuthentication().equals(Common.AUTHENTICATION)) {
            return R.ok();
        }*/
        String host = "https://checkid.market.alicloudapi.com";       //�����ַ  ֧��http �� https �� WEBSOCKET
        String path = "/IDCard";                                     //��׺
        String appcode = "188cbe4f58fa44e09f122ada0ef8934e";                             //AppCode  ���Լ���AppCode ��������Ĳ鿴
        String idCard = idnum;                                     //�������������api�ӿڲ���
        String name = realname;                                            //�������������api�ӿڲ���
        String urlSend = host + path + "?idCard=" + idCard + "&name=" + name;   //ƴ����������
        URL url = new URL(urlSend);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "APPCODE " + appcode);//��ʽAuthorization:APPCODE (�м���Ӣ�Ŀո�)
        int httpCode = httpURLConnection.getResponseCode();
        String json = read(httpURLConnection.getInputStream());
        String code = JSON.parseObject(json).getString("status");
       /* if (code.equals("01") || code.equals("1")) {
            info.setIsAuthentication(Common.AUTHENTICATION);
            userFeign.updateUserInfoById(info);
        }*/
        Map<String, Object> map = new HashMap<>();
        switch (code) {
            case "01":
                map.put("code", "0");
                map.put("msg", "ʵ����֤ͨ��!");
                break;
            case "02":
                map.put("code", "02");
                map.put("msg", "ʵ����֤��ͨ����!");
                break;
            case "202":
                map.put("code", "202");
                map.put("msg", "�޷���֤��");
                break;
            case "203":
                map.put("code", "203");
                map.put("msg", "�쳣�����");
                break;
            case "204":
                map.put("code", "204");
                map.put("msg", "������ʽ����ȷ��");
                break;
            case "205":
                map.put("code", "205");
                map.put("msg", "���֤��ʽ����ȷ��");
                break;

        }
        /**
         * code 01 ʵ����֤ͨ����
         *      02 ʵ����֤��ͨ����
         *      202	�޷���֤��
         *      203	�쳣�����
         *      204	������ʽ����ȷ��
         *      205	���֤��ʽ����ȷ��
         */
        return R.ok(map);
    }

    /*
       ��ȡ���ؽ��
    */
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}


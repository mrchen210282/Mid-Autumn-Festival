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
@Api(value = "实名Con", tags = {"用户上传sfz图片"})
public class Confirm {

    @Autowired
    private UserFeign userFeign;

    //@Login  @RequestAttribute("uid") String uid
    @PostMapping("authentication")
    @ApiOperation("实名认证")
    public R uploadImgMessage(@ApiParam @RequestParam String realname, @ApiParam @RequestParam String idnum
                             ) throws Exception {

       /* UserInfoEntity info = userFeign.selectUserinfoById(uid);
        if (info.getIsAuthentication().equals(Common.AUTHENTICATION)) {
            return R.ok();
        }*/
        String host = "https://checkid.market.alicloudapi.com";       //请求地址  支持http 和 https 及 WEBSOCKET
        String path = "/IDCard";                                     //后缀
        String appcode = "188cbe4f58fa44e09f122ada0ef8934e";                             //AppCode  你自己的AppCode 在买家中心查看
        String idCard = idnum;                                     //参数，具体参照api接口参数
        String name = realname;                                            //参数，具体参照api接口参数
        String urlSend = host + path + "?idCard=" + idCard + "&name=" + name;   //拼接请求链接
        URL url = new URL(urlSend);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "APPCODE " + appcode);//格式Authorization:APPCODE (中间是英文空格)
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
                map.put("msg", "实名认证通过!");
                break;
            case "02":
                map.put("code", "02");
                map.put("msg", "实名认证不通过！!");
                break;
            case "202":
                map.put("code", "202");
                map.put("msg", "无法验证！");
                break;
            case "203":
                map.put("code", "203");
                map.put("msg", "异常情况！");
                break;
            case "204":
                map.put("code", "204");
                map.put("msg", "姓名格式不正确！");
                break;
            case "205":
                map.put("code", "205");
                map.put("msg", "身份证格式不正确！");
                break;

        }
        /**
         * code 01 实名认证通过！
         *      02 实名认证不通过！
         *      202	无法验证！
         *      203	异常情况！
         *      204	姓名格式不正确！
         *      205	身份证格式不正确！
         */
        return R.ok(map);
    }

    /*
       读取返回结果
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


package cn.bitflash.manage;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.controller.Confirm;
import cn.bitflash.vip.user.feign.UserFeign;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("manage")
public class ManageController {

    @Autowired
    private UserFeign userFeign;

    /**
     *  实名认证接口
     *  AppKey：25198027
     *  AppSecret：b38da61305dc3daf657cfccb4d86cd87
     *  AppCode：ec04e916a3a74ca39247a27a3f68e38f
     */
    @PostMapping("authentication")
    @ApiOperation("实名认证")
    public R uploadImgMessage(@RequestParam("uid") String uid,
                              @ApiParam @RequestParam String realname, @ApiParam @RequestParam String idnum
    ) throws Exception {

        UserInfoEntity info = userFeign.selectUserinfoById(uid);
        if (info.getIsAuth().equals(Common.AUTHENTICATION)) {
            return R.ok();
        }
        //请求地址  支持http 和 https 及 WEBSOCKET
        String host = "https://checkid.market.alicloudapi.com";
        //后缀
        String path = "/IDCard";
        //AppCode  你自己的AppCode 在买家中心查看
        String appcode = "ec04e916a3a74ca39247a27a3f68e38f";
        //参数，具体参照api接口参数
        String idCard = idnum;
        //参数，具体参照api接口参数
        String name = realname;
        //拼接请求链接
        String urlSend = host + path + "?idCard=" + idCard + "&name=" + name;
        URL url = new URL(urlSend);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //格式Authorization:APPCODE (中间是英文空格)
        httpURLConnection.setRequestProperty("Authorization", "APPCODE " + appcode);
        //int httpCode = httpURLConnection.getResponseCode();
        String json = Confirm.read(httpURLConnection.getInputStream());
        String code = JSON.parseObject(json).getString("status");
        if (code.equals("01") || code.equals("1")) {
            info.setIsAuth(Common.AUTHENTICATION);
            info.setRealname(realname);
            info.setIdNumber(idnum);
            userFeign.updateUserInfoById(info);
        }
        Map<String, Object> map = new HashMap<>();
        switch (code) {
            case "01":
                map.put("status", "0");
                map.put("msg", "实名认证通过!");
                break;
            case "02":
                map.put("status", "02");
                map.put("msg", "实名认证不通过！!");
                break;
            case "202":
                map.put("status", "202");
                map.put("msg", "无法验证！");
                break;
            case "203":
                map.put("status", "203");
                map.put("msg", "异常情况！");
                break;
            case "204":
                map.put("status", "204");
                map.put("msg", "姓名格式不正确！");
                break;
            case "205":
                map.put("status", "205");
                map.put("msg", "身份证格式不正确！");
                break;

        }
        /**
         * code 01 实名认证通过！
         *      02 实名认证不通过！
         *      202  无法验证！
         *      203  异常情况！
         *      204  姓名格式不正确！
         *      205  身份证格式不正确！
         */
        return R.ok(map);
    }
}

package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.util.Common;
import cn.bitflash.util.HttpUtils;
import cn.bitflash.util.R;
import cn.bitflash.vip.user.feign.UserFeign;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "实名Con", tags = {"用户上传sfz图片"})
public class Confirm {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("uploadSFZ")
    @ApiOperation("实名认证")
    public R uploadImgMessage(@ApiParam @RequestParam String realname,@ApiParam @RequestParam String idnum,
                              @RequestAttribute("uid") String uid) {
        UserInfoEntity info = userFeign.selectUserinfoById(uid);
        if (info.getIsAuthentication().equals(Common.AUTHENTICATION)) {
            return R.ok();
        }
        String host = "https://checkid.market.alicloudapi.com";
        String path = "/IDCard";
        String method = "GET";
        String appcode = "188cbe4f58fa44e09f122ada0ef8934e";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("idCard", idnum);
        querys.put("name", realname);
        String code = null;
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String msg = EntityUtils.toString(response.getEntity());
            code = JSON.parseObject(msg).getString("status");
            if (code.equals("01") || code.equals("1")) {
                info.setIsAuthentication(Common.AUTHENTICATION);
                userFeign.updateUserInfoById(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * code 01 实名认证通过！
         *      02 实名认证不通过！
         *      202	无法验证！
         *      203	异常情况！
         *      204	姓名格式不正确！
         *      205	身份证格式不正确！
         */
        return R.ok(code);
    }
}


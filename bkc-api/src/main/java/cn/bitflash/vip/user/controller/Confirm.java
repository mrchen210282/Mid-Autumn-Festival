package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserPayUrlEntity;
import cn.bitflash.interceptor.ApiLoginInterceptor;
import cn.bitflash.util.R;
import cn.bitflash.vip.user.feign.UserFeign;
import com.gexin.rp.sdk.base.uitls.MD5Util;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/user")
@Api(value = "实名Con", tags = {"用户上传sfz图片"})
public class Confirm {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("uploadSFZ")
    public R uploadImgMessage(@RequestParam String img, @RequestParam String imgType,
                              @RequestAttribute("uid") String uid) {
        String mobile = userFeign.selectUserInfoByColumn(uid,"mobile");

        String imgName = imgType.equals("3") ? MD5Util.getMD5Format(mobile+ System.currentTimeMillis()) + "_z" : MD5Util.getMD5Format(mobile + System.currentTimeMillis()) + "_f";
        String imgUrl = "";
        String path = "/home/statics/idnumber/" + imgName + ".png";
        imgUrl = "http://www.bitflash.vip/auth/" + imgName + ".png";
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            String[] base64Str = img.split(",");
            if(base64Str.length >= 2) {
                byte[] b = decoder.decodeBuffer(base64Str[1]);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {// 调整异常数据
                        b[i] += 256;
                    }
                }
                OutputStream out = new FileOutputStream(path);
                out.write(b);
                out.flush();
                out.close();
            } else {
                return R.error();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        UserPayUrlEntity userPay = userFeign.selectUserPayUrlByUidAndType(uid,imgType);
        //为空代表第一次上传图片插入操作
        if (userPay == null) {
            userPay = new UserPayUrlEntity();
            userPay.setImgType(imgType);
            userPay.setCrateTime(new Date());
            userPay.setUid(uid);
            userPay.setImgUrl(imgUrl);
            userPay.setMobile(mobile);
            userFeign.insertUserUrl(userPay);
        } else {
            userPay.setImgUrl(imgUrl);
            userFeign.updateUserUrlById(userPay);
        }
        return R.ok();
    }

    @Login
    @PostMapping("successUpload")
    public R successUpload(@RequestAttribute(ApiLoginInterceptor.UID) String uid) {
        UserInfoEntity userinfo = new UserInfoEntity();
        userinfo.setIsAuthentication("1");
        userinfo.setUid(uid);
        userFeign.updateUserInfoById(userinfo);
        return R.ok();
    }


}

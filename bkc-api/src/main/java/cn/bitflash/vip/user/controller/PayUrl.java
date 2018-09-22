package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserEntity;
import cn.bitflash.entity.UserPayImgEntity;
import cn.bitflash.entity.UserPayPwdEntity;
import cn.bitflash.util.R;
import cn.bitflash.util.ValidatorUtils;
import cn.bitflash.vip.user.entity.ImgForm;
import cn.bitflash.vip.user.feign.UserFeign;
import com.gexin.rp.sdk.base.uitls.MD5Util;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/user")
@Api(value = "支付信息Con", tags = {"上传，查看"})
public class PayUrl {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("upload")
    @ApiOperation("上传支付码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "img",dataType = "String"),
            @ApiImplicitParam(name = "imgType",dataType = "String"),
            @ApiImplicitParam(name = "name",dataType = "String"),
            @ApiImplicitParam(name = "account",dataType = "String"),
            @ApiImplicitParam(name = "password",dataType = "String")
    })
    public R upload(@RequestBody ImgForm imgForm, @RequestAttribute("uid") String uid) {
        ValidatorUtils.validateEntity(imgForm);

        UserEntity user = userFeign.selectUserByUid(uid);

        //验证交易密码
        String password = imgForm.getPassword();
        UserPayPwdEntity pwdEntity = userFeign.selectUserPwdByUid(uid);
        if (!password.equals(pwdEntity.getPayPassword())) {
            return R.error("交易密码错误");
        }

        String imgType = imgForm.getImgType();
        String imgUrl = "";
        String path = "/home/statics/qrcode/";
        //String path = "D:\\upload\\img\\";
        String md5 = MD5Util.getMD5Format(user.getMobile() + System.currentTimeMillis());
        switch (imgType) {
            case "1":
                String md5_w = md5 + "_w";
                path = path + md5_w + ".png";
                imgUrl = "http://www.bitflash.vip/qrcode/" + md5_w + ".png";
                break;
            case "2":
                String md5_z = md5 + "_z";
                path = path + md5_z + ".png";
                imgUrl = "http://www.bitflash.vip/qrcode/" + md5_z + ".png";
                break;
            case "5":
                String md5_c = md5 + "_c";
                path = path + md5_c + ".png";
                imgUrl = "http://www.bitflash.vip/qrcode/" + md5_c + ".png";
                break;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            String[] base64Str = imgForm.getImg().split(",");
            if (base64Str.length >= 2) {
                byte[] b = decoder.decodeBuffer(base64Str[1]);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {// 调整异常数据
                        b[i] += 256;
                    }
                }
                // 生成jpeg图片
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

        // 先查询是否已上传过图片，如果已上传则使用最新上传的图片
        UserPayImgEntity userPayUrlEntity = userFeign.selectUserPayUrlByUidAndType(uid, imgType);
        if (userPayUrlEntity == null) {
            userPayUrlEntity = new UserPayImgEntity();
            userPayUrlEntity.setImgType(imgType);
            userPayUrlEntity.setAccount(imgForm.getAccount());
            userPayUrlEntity.setCrateTime(new Date());
            userPayUrlEntity.setImgUrl(imgUrl);
            userPayUrlEntity.setMobile(user.getMobile());
            userPayUrlEntity.setAccountName(imgForm.getName());
            userPayUrlEntity.setUid(user.getUid());
            userFeign.insertUserUrl(userPayUrlEntity);
        } else {
            userPayUrlEntity.setImgUrl(imgUrl);
            userPayUrlEntity.setAccount(imgForm.getAccount());
            userPayUrlEntity.setAccountName(imgForm.getName());
            userFeign.updateUserUrlById(userPayUrlEntity);
        }
        return R.ok();
    }


    @Login
    @PostMapping("getPayUrl")
    @ApiOperation("获取图片地址")
    public R getPayUrl(@RequestAttribute("uid") String myuid, @ApiParam @RequestParam(value = "uid", required = false) String uid,@ApiParam @RequestParam("imgType") String imgType) {
        UserPayImgEntity payUrlEntity = null;
        if (uid == null) {
            payUrlEntity = userFeign.selectUserPayUrlByUidAndType(myuid, imgType);
        } else {
            payUrlEntity = userFeign.selectUserPayUrlByUidAndType(uid, imgType);
        }
        if (payUrlEntity == null) {
            return R.error("未上传收款信息");
        }
        if (payUrlEntity.getAccountName() != null && payUrlEntity.getAccount() != null) {
            return R.ok().put("url", payUrlEntity.getImgUrl()).put("name", payUrlEntity.getAccountName()).put("account", payUrlEntity.getAccount());
        }
        return R.ok().put("url", payUrlEntity.getImgUrl());
    }


}

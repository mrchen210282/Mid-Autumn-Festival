package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserLoginEntity;
import cn.bitflash.entity.UserMobilePaymentInfoEntity;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.R;
import cn.bitflash.utils.ValidatorUtils;
import cn.bitflash.vip.user.entity.ImgForm;
import cn.bitflash.vip.user.feign.UserFeign;
import com.gexin.rp.sdk.base.uitls.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "支付信息Con", tags = {"上传，查看"})
public class PayUrl {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("uploadPayment")
    @ApiOperation("上传支付码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "img", dataType = "String"),
            @ApiImplicitParam(name = "imgType", dataType = "String"),
            @ApiImplicitParam(name = "account", dataType = "String")
    })
    public R uploadPayment(@RequestBody ImgForm imgForm, @RequestAttribute("uid") String uid) {
        ValidatorUtils.validateEntity(imgForm);
        UserLoginEntity user = userFeign.selectUserLoginByUid(uid);
        String path = "/home/statics/qrcode/";
        String imgName = MD5Util.getMD5Format(user.getMobile() + System.currentTimeMillis());
        switch (imgForm.getImgType()) {
            case Common.WECHAT:
                imgName = imgName + "_w.png";
                path = path + imgName;
                break;
            case Common.ALIPAY:
                imgName = imgName + "_z.png";
                path = path + imgName;
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
        UserMobilePaymentInfoEntity payment = userFeign.selectPaymentByUidAndType(uid, imgForm.getImgType());
        if (payment == null) {
            payment = new UserMobilePaymentInfoEntity();
            payment.setUid(uid);
            payment.setAccount(imgForm.getAccount());
            payment.setType(imgForm.getImgType());
            payment.setCode(imgName);
            userFeign.insertUserPayment(payment);
        } else {
            payment.setCode(imgName);
            userFeign.updateUserPaymentById(payment);
        }

        return R.ok();
    }


    @Login
    @PostMapping("getPayUrl")
    @ApiOperation("获取图片地址")
    public R getPayUrl(@RequestAttribute("uid") String uid) {
        List<UserMobilePaymentInfoEntity> payment = userFeign.selectPaymentsByUid(uid);
        if (payment == null) {
            return R.error("未上传收款信息");
        }
        Map<String, Object> map = new HashMap<>();
        map.put(Common.ALIPAY, "0");
        map.put(Common.WECHAT, "0");
        payment.stream().forEach(u -> {
            if (u.getType().equals(Common.ALIPAY)) {
                map.put(Common.ALIPAY, "1");
            }
            if (u.getType().equals(Common.WECHAT)) {
                map.put(Common.WECHAT, "1");
            }
        });
        return R.ok(map);
    }


}

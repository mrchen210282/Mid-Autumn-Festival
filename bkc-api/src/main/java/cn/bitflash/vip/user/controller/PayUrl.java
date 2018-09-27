package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserBankPaymentInfoEntity;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
        //String path = "/home/statics/qrcode/";
        String path = "D:/";
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
    @ApiOperation("获取支付方式")
    public R getPayUrl(@RequestAttribute("uid") String uid) {
        List<UserMobilePaymentInfoEntity> payment = userFeign.selectPaymentsByUid(uid);
        if (payment == null) {
            return R.error("未上传收款信息");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        payment.stream().forEach(u -> {
            if (u.getType().equals(Common.ALIPAY)) {
                list.add(new ModelMap("name", "支付宝").addAttribute("type", Common.ALIPAY));
            }
            if (u.getType().equals(Common.WECHAT)) {
                list.add(new ModelMap("name", "微信").addAttribute("type", Common.WECHAT));
            }
        });
        UserBankPaymentInfoEntity paymentInfo = userFeign.selectBankInfoByUid(uid);
        if (paymentInfo != null) {
            list.add(new ModelMap("name", "银行卡").addAttribute("type", Common.BANK));
        }
        return R.ok(new ModelMap("msg", list));
    }

    @Login
    @PostMapping("uploadBankMess")
    @ApiOperation("上传银行信息")
    public R uploadBankMess(@RequestParam String bank, @RequestParam String cardNo, @RequestAttribute("uid") String uid) {
        UserBankPaymentInfoEntity bankInfo = new UserBankPaymentInfoEntity();
        bankInfo.setUid(uid);
        bankInfo.setBank(bank);
        bankInfo.setCardNo(cardNo);
        userFeign.insertOrUpdateBank(bankInfo);
        return R.ok();
    }

    @Login
    @PostMapping("getBankPaymentInfo")
    @ApiOperation("获取银行信息")
    public R getBankPaymentInfo(@RequestAttribute("uid") String uid) {
        UserBankPaymentInfoEntity bankInfo = userFeign.selectBankInfoByUid(uid);
        return R.ok(new ModelMap("bank", bankInfo));
    }

    @Login
    @PostMapping("getMobilePaymentInfo")
    @ApiOperation("获取手机支付方式")
    public R getMobilePaymentInfo(@RequestAttribute("uid")String uid,@RequestParam String type){
        UserMobilePaymentInfoEntity mobile = userFeign.selectPaymentByUidAndType(uid,type);
        String address = userFeign.getPath(2);
        return R.ok(new ModelMap("account",mobile.getAccount()).addAttribute("uri",address+mobile.getCode()));
    }


}

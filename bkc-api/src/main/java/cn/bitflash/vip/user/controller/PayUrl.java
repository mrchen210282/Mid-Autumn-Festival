package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.util.R;
import cn.bitflash.util.ValidatorUtils;
import cn.bitflash.vip.user.entity.ImgForm;
import cn.bitflash.vip.user.feign.UserFeign;
import com.gexin.rp.sdk.base.uitls.MD5Util;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "支付信息Con", tags = {"上传，查看"})
public class PayUrl {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("upload")
    @Transactional
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
        UserPayUrlEntity userPayUrlEntity = userFeign.selectUserPayUrlByUidAndType(uid, imgType);
        if (userPayUrlEntity == null || userPayUrlEntity.getId() == null) {
            userPayUrlEntity = new UserPayUrlEntity();
            userPayUrlEntity.setImgType(imgType);
            userPayUrlEntity.setAccount(imgForm.getAccount());
            userPayUrlEntity.setCrateTime(new Date());
            userPayUrlEntity.setImgUrl(imgUrl);
            userPayUrlEntity.setMobile(user.getMobile());
            userPayUrlEntity.setName(imgForm.getName());
            userPayUrlEntity.setUid(user.getUid());
            userFeign.insertUserUrl(userPayUrlEntity);
        } else {
            userPayUrlEntity.setImgUrl(imgUrl);
            userPayUrlEntity.setAccount(imgForm.getAccount());
            userPayUrlEntity.setName(imgForm.getName());
            userFeign.updateUserUrlById(userPayUrlEntity);
        }
        return R.ok();
    }

    @Login
    @PostMapping("getPayMessage")
    public R getPayMessage(@RequestParam("accountId") String accountId, @RequestParam("type") String type) {
        String uid = null;
        //交易订单
        if (type.equals("1")) {
            UserTradeEntity tradeEntity = userFeign.selectTradeById(accountId);
            uid = tradeEntity.getUid();
        }
        //求购订单
        else if (type.equals("2")) {
            UserBuyHistoryEntity userBuyEntity = userFeign.selectBuyHistoryById(accountId);
            uid = userBuyEntity.getSellUid();
        }
        List<UserPayUrlEntity> payUrlEntities = userFeign.selectUserUrlList(uid);
        if (payUrlEntities == null || payUrlEntities.size() == 0) {
            return R.error("未设置支付信息");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        payUrlEntities.stream().forEach(u -> {
            if (u.getImgType().equals("1")) {
                list.add(new ModelMap("name", "微信").addAttribute("type", 1));
            }
            if (u.getImgType().equals("2")) {
                list.add(new ModelMap("name", "支付宝").addAttribute("type", 2));
            }
            if (u.getImgType().equals("5")) {
                list.add(new ModelMap("name", "银行卡").addAttribute("type", 5));
            }
        });

        return R.ok().put("url", list).put("uid", uid);
    }

    @Login
    @PostMapping("getPayUrl")
    public R getPayUrl(@RequestAttribute("uid") String myuid, @RequestParam(value = "uid", required = false) String uid, @RequestParam("imgType") String imgType) {
        UserPayUrlEntity payUrlEntity = null;
        if (uid == null) {
            payUrlEntity = userFeign.selectUserPayUrlByUidAndType(myuid, imgType);
        } else {
            payUrlEntity = userFeign.selectUserPayUrlByUidAndType(uid, imgType);
        }
        if (payUrlEntity == null) {
            return R.error("未上传收款信息");
        }
        if (payUrlEntity.getName() != null && payUrlEntity.getAccount() != null) {
            return R.ok().put("url", payUrlEntity.getImgUrl()).put("name", payUrlEntity.getName()).put("account", payUrlEntity.getAccount());
        }
        return R.ok().put("url", payUrlEntity.getImgUrl());
    }


}

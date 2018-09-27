package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserCashAssetsEntity;
import cn.bitflash.entity.UserDrawingEntity;
import cn.bitflash.entity.UserLoginEntity;
import cn.bitflash.utils.Encrypt;
import cn.bitflash.utils.R;
import cn.bitflash.utils.ValidatorUtils;
import cn.bitflash.vip.user.entity.CashForm;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("cash")
public class Cash {


    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("getTotleIncome")
    @ApiOperation("获取可提现金额")
    public R getTotleIncome(@RequestAttribute("uid") String uid) {
        UserCashAssetsEntity cashAssets = userFeign.selectCashAssetsByUid(uid);
        return R.ok(cashAssets.getWithdrawCash().toString());    }

    @Login
    @PostMapping("insertCash")
    @ApiOperation("加入提现记录表")
    public R cashing(@RequestBody CashForm cashForm, @RequestAttribute("uid") String uid) {

        // 表单校验
        ValidatorUtils.validateEntity(cashForm);
        UserLoginEntity user = userFeign.selectUserLoginByUid(uid);
        if (!user.getPassword().equals(Encrypt.SHA256(cashForm.getPasswd() + user.getSalt()))) {
            return R.error("登录密码错误");
        }
        //提现金额
        BigDecimal money = cashForm.getCashMoney();
        UserCashAssetsEntity cashAssets = userFeign.selectCashAssetsByUid(uid);
        //插入提现记录表
        UserDrawingEntity drawing = new UserDrawingEntity();
        String orderId = "00" + RandomStringUtils.randomNumeric(6);
        drawing.setId(orderId);
        drawing.setUid(uid);
        drawing.setMoney(money);
        drawing.setCreateTime(new Date());
        drawing.setType(cashForm.getCashType());
        Boolean flag = userFeign.insertDrawingInfo(drawing);
        if(flag){
            //扣除提现剩余金额
            BigDecimal cashMoney = cashAssets.getWithdrawCash().subtract(money);
            if (money.compareTo(cashAssets.getWithdrawCash()) == 1) {
                return R.error("提现金额超出累计收益");
            }
            cashAssets.setWithdrawCash(cashMoney);
            userFeign.updateCashAssetsByUid(cashAssets);
        }else{
            userFeign.deleteDrawingInfo(orderId);
        }

        return R.ok();

    }

}

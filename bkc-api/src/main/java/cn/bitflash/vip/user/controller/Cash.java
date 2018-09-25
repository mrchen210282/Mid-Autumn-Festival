package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserCashAssetsEntity;
import cn.bitflash.entity.UserDrawingInfoEntity;
import cn.bitflash.entity.UserLoginEntity;
import cn.bitflash.utils.Encrypt;
import cn.bitflash.utils.R;
import cn.bitflash.utils.RandomNumUtil;
import cn.bitflash.utils.ValidatorUtils;
import cn.bitflash.vip.user.entity.CashForm;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public R getTotleIncome(@RequestAttribute("uid")String uid){
        UserCashAssetsEntity cashAssets = userFeign.selectCashAssetsByUid(uid);
        return R.ok(cashAssets.getTotleIncome().toString());
    }

    @Login
    @PostMapping("insertCash")
    @ApiOperation("加入提现记录表")
    public R cashing( @RequestBody CashForm cashForm, @RequestAttribute("uid")String uid){

        // 表单校验
        ValidatorUtils.validateEntity(cashForm);
        UserLoginEntity user = userFeign.selectUserLoginByUid(uid);
        if(!user.getPassword().equals(Encrypt.SHA512(cashForm.getPasswd()+user.getSalt()))){
            return R.error("登录密码错误");
        }
        //提现金额
        BigDecimal money = cashForm.getCashMoney();
        UserCashAssetsEntity cashAssets = userFeign.selectCashAssetsByUid(uid);
        //扣除提现剩余金额
        BigDecimal cashMoney = cashAssets.getTotleIncome().subtract(money);
        if(money.compareTo(cashAssets.getTotleIncome())==1){
            return R.error("提现金额超出累计收益");
        }
        cashAssets.setTotleIncome(cashMoney);
        userFeign.updateCashAssetsByUid(cashAssets);
        UserDrawingInfoEntity drawingInfo = new UserDrawingInfoEntity();
        drawingInfo.setId(RandomNumUtil.nBit(8));
        drawingInfo.setUid(uid);
        drawingInfo.setCashMoney(money);
        drawingInfo.setCreateTime(new Date());
        drawingInfo.setCashType(cashForm.getCashType());
        userFeign.insertDrawingInfo(drawingInfo);
        return R.ok();

    }
}

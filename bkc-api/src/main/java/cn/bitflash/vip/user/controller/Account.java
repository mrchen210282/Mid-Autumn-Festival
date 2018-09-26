package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserCashAssetsEntity;
import cn.bitflash.entity.UserCashAssetsJoinDictComputingPowerBean;
import cn.bitflash.entity.UserDigitalAssetsEntity;
import cn.bitflash.entity.UserPerformanceEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "账户数量Con", tags = {"首页显示数据"})
public class Account {

    private final Logger logger = LoggerFactory.getLogger(Account.class);

    @Autowired
    private UserFeign userFeign;

    //@Login
    @PostMapping("getIndexAssets")
    public R getIndexAssets(@RequestAttribute("uid") String uid){

        Map<String,Object> map = new HashMap<String,Object>();

        //查询数字资产
        UserDigitalAssetsEntity userDigitalAssetsEntity = userFeign.selectDigitalAssetsById(uid);

        //查询现金资产
        UserCashAssetsJoinDictComputingPowerBean userCashAssetsJoinDictComputingPowerBean = userFeign.selectUserCashAssetsJoinDictComputingPower(uid);

        //查询社区考核
        UserPerformanceEntity userPerformanceEntity = userFeign.selectUserPerformanceById(uid);

        if(null != userDigitalAssetsEntity) {
            map.put("purchase",userDigitalAssetsEntity.getPurchase());
            map.put("frozen",userDigitalAssetsEntity.getFrozen());
            map.put("available",userDigitalAssetsEntity.getAvailable());
        } else {
            logger.info("查询数字资产用户:" + uid + "为空！");
        }

        if(null != userCashAssetsJoinDictComputingPowerBean) {
            map.put("rate",userCashAssetsJoinDictComputingPowerBean.getRate());
            map.put("totalIncome",userCashAssetsJoinDictComputingPowerBean.getTotleIncome());
            map.put("withdrawCash",userCashAssetsJoinDictComputingPowerBean.getWithdrawCash());
            map.put("lotto",userCashAssetsJoinDictComputingPowerBean.getLotto());
        } else {
            logger.info("查询现金资产用户:" + uid + "为空！");
        }

        if(null != userPerformanceEntity) {
            map.put("line1",userPerformanceEntity.getLine1());
            map.put("line2",userPerformanceEntity.getLine2());
            map.put("line3",userPerformanceEntity.getLine3());
        } else {
            logger.info("查询社区考核用户:" + uid + "为空！");
        }
        return R.ok(map);
    }
}

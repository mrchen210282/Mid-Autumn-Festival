package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "账户数量Con", tags = {"首页显示数据"})
public class Account {

    private final Logger logger = LoggerFactory.getLogger(Account.class);

    @Autowired
    private UserFeign userFeign;

    /**
     * 首页资产信息
     * @param uid
     * @return
     */
    @Login
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
            map.put("purchase",userDigitalAssetsEntity.getPurchase().toString());
            map.put("frozen",userDigitalAssetsEntity.getFrozen().toString());
            map.put("available",userDigitalAssetsEntity.getAvailable().toString());
        } else {
            logger.info("查询数字资产用户:" + uid + "为空！");
        }

        if(null != userCashAssetsJoinDictComputingPowerBean) {
            if(null != userCashAssetsJoinDictComputingPowerBean.getRate()) {
                map.put("rate",userCashAssetsJoinDictComputingPowerBean.getRate().toString());
            } else {
                map.put("rate","0.00");
            }

            if(null != userCashAssetsJoinDictComputingPowerBean.getTotleIncome()) {
                map.put("totalIncome",userCashAssetsJoinDictComputingPowerBean.getTotleIncome().toString());
            } else {
                map.put("totalIncome","0.00");
            }

            if(null != userCashAssetsJoinDictComputingPowerBean.getWithdrawCash()) {
                map.put("withdrawCash",userCashAssetsJoinDictComputingPowerBean.getWithdrawCash().toString());
            } else {
                map.put("withdrawCash","0.00");
            }

            if(null != userCashAssetsJoinDictComputingPowerBean.getLotto()) {
                map.put("lotto",userCashAssetsJoinDictComputingPowerBean.getLotto().toString());
            } else {
                map.put("lotto","0.00");
            }
        } else {
            logger.info("查询现金资产用户:" + uid + "为空！");
        }

        if(null != userPerformanceEntity) {
            map.put("line1",userPerformanceEntity.getLine1().toString());
            map.put("line2",userPerformanceEntity.getLine2().toString());
            map.put("line3",userPerformanceEntity.getLine3().toString());
        } else {
            logger.info("查询社区考核用户:" + uid + "为空！");
        }
        return R.ok(map);
    }
}

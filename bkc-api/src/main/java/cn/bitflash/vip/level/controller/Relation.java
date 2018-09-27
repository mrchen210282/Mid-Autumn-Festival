package cn.bitflash.vip.level.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserCashAssetsEntity;
import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.entity.UserPerformanceEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.level.feign.LevelFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/level")
@Api(value = "我的社区Con", tags = {"显示柱状图"})
public class Relation {

    @Autowired
    private LevelFeign levelFeign;

    @Login
    @PostMapping("getRelation")
    @ApiOperation("显示社区详情")
    public R getRelation(@RequestAttribute("uid") String uid) {
        UserCashAssetsEntity cashAssets = levelFeign.selectCashAssetsByUid(uid);
        if (cashAssets.getPowerLevel() == null || cashAssets.getPowerLevel() == 0) {
            return R.error("尚未加入社区");
        }
        Map<String, Object> map = new HashMap<>();
        UserPerformanceEntity user = levelFeign.selectPerformanceByUid(uid);

        BigDecimal[] lines = {user.getLine1(),user.getLine2(),user.getLine3()};
        int i = 0 ;
        map.put("show",i);
        for(BigDecimal line:lines){
           if(line.doubleValue()>0.0d){
               map.put("show",++i);
           }
        }
        map.put("line", user);
        UserInvitationCodeEntity code = levelFeign.selectInvitationCodeByUid(uid);
        String address = levelFeign.getPath(4);
        map.put("address", address + code.getCode());

        return R.ok(map);


    }


}
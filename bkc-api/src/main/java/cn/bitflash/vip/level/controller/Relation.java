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
        if (cashAssets.getLevel() == null || cashAssets.getLevel() == 0) {
            return R.ok("尚未加入社区");
        }
        Map<String, Object> map = new HashMap<>();
        UserPerformanceEntity user = levelFeign.selectPerformanceByUid(uid);
        map.put("line", user);
        UserInvitationCodeEntity code = levelFeign.selectInvitationCodeByUid(uid);
        String adress = levelFeign.getPath(4);
        map.put("adress", adress + code);
        return R.ok(map);


    }


}

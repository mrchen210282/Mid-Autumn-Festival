package cn.bitflash.vip.level.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.entity.UserRelationEntity;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.vip.level.feign.LevelFeign;
import io.swagger.annotations.Api;
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
    public R getRelation(@RequestAttribute("uid") String uid) {

        UserInfoEntity infoEntity = levelFeign.selectUserInfoByUid(uid);
        if (Integer.valueOf(infoEntity.getIsVip()) < 0) {
            return R.error("没有加入社区");
        }
        //返回map
        UserAccountEntity userAccount = levelFeign.selectAccountByUid(uid);
        Map<String, Object> map = new HashMap<String, Object>();
        if (userAccount != null) {
            Double left = userAccount.getLftAchievement().doubleValue();
            Double right = userAccount.getRgtAchievement().doubleValue();
            /**
             * 左右区显示比率最低为10%，最高为70%
             */
            String leftRate = "10%";
            if (left != 0) {
                leftRate = (((Math.round(left / (left + right)) * 0.6) + 0.1) * 100) + "%";
            }
            String rightRate = "10%";
            if (right != 0) {
                rightRate = (((Math.round(right / (left + right)) * 0.6) + 0.1) * 100) + "%";
            }
            map.put("leftRate", leftRate);
            map.put("rightRate", rightRate);
            map.put("lft_a", left);
            map.put("rgt_a", right);
        }
        UserInvitationCodeEntity userInvitationCode = levelFeign.selectInvitationCodeByUid(uid);
        UserRelationEntity ur = levelFeign.selectRelationByCode(userInvitationCode.getLftCode());
        String address = levelFeign.getVal(Common.ADDRESS);
        map.put("leftAddress", address + userInvitationCode.getLftCode());
        map.put("showleft", 200);
        if (ur != null) {
            map.put("rightAddress", address + userInvitationCode.getRgtCode());
            map.put("showright", 200);
        } else {
            map.put("rightAddress", "");
            map.put("showright", 500);
        }
        return R.ok().put("myRelation", map);
    }
}

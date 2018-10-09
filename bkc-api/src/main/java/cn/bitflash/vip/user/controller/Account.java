package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserAssetsHlbBean;
import cn.bitflash.entity.UserAssetsNpcEntity;
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

    /**
     * 首页资产信息
     *
     * @param id
     * @return
     */
    @Login
    @PostMapping("getIndexAssets")
    public R getIndexAssets(@RequestAttribute("id") String id) {

        Map<String, Object> map = new HashMap<String, Object>();

        //我的NPC
        UserAssetsNpcEntity userAssetsNpcEntity = userFeign.selectbyid(id);

        //我的HLB
        UserAssetsHlbBean userAssetsHlbBean = userFeign.selectHlbCommunity(id);

        if (null != userAssetsNpcEntity) {
            map.put("NPCtotalAssets", userAssetsNpcEntity.getTotelAssets());
            map.put("NPCfrozen", userAssetsNpcEntity.getFrozenAssets());
            map.put("NPCavailable", userAssetsNpcEntity.getAvailableAssets());
        } else {
            map.put("NPCtotalAssets", 0.00f);
            map.put("NPCfrozen", 0.00f);
            map.put("NPCavailable", 0.00f);
            logger.info("查询NPC资产用户:" + id + "为空！");
        }

        if (null != userAssetsHlbBean) {
            map.put("HLBtotalAssets", userAssetsHlbBean.getTotelAssets());
            map.put("HLBfrozen", userAssetsHlbBean.getFrozenAssets());
            map.put("HLBavailable", userAssetsHlbBean.getAvailableAssets());
            map.put("lftAchievement",userAssetsHlbBean.getLftAchievement());
            map.put("rgtAchievement",userAssetsHlbBean.getRgtAchievement());
            map.put("daliyRelease",userAssetsHlbBean.getDailyRelease());
            map.put("powerRelease",userAssetsHlbBean.getPower());
        } else {
            map.put("HLBtotalAssets", 0.00f);
            map.put("HLBfrozen", 0.00f);
            map.put("HLBavailable", 0.00f);
            map.put("lftAchievement",0.00f);
            map.put("rgtAchievement",0.00f);
            map.put("daliyRelease",0.00f);
            map.put("powerRelease","0.00%");

            logger.info("查询HLB资产用户:" + id + "为空！");
        }
        return R.ok(map);
    }


}

package cn.bitflash.vip.level.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.R;
import cn.bitflash.vip.level.entity.UserInfoBean;
import cn.bitflash.vip.level.feign.LevelFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        UserAssetsHlbEntity hlbEntity = levelFeign.selectUserAssetsHlbById(uid);
        UserInfoEntity userInfo = levelFeign.selectUserInfoByUid(uid);
        Map<String, Object> map = new HashMap<>();
        //左区
        map.put("left", hlbEntity.getLftAchievement());
        //右区
        map.put("right", hlbEntity.getRgtAchievement());
        //释放额度
        SystemVipEntity systemVipEntity = levelFeign.selectSystemVipById(userInfo.getVipLevel());
        map.put("now_amount", systemVipEntity.getVipCash());
        //当前算力
        Integer specialPower = Integer.valueOf(levelFeign.getVal("special_level"));

        SystemPowerEntity systemPowerEntity = levelFeign.selectSystemPowerById(userInfo.getUpgradeNum());
        BigDecimal power = new BigDecimal(systemPowerEntity.getPower() * 100);
        if(userInfo.getVipLevel().equals(specialPower)){
            power = new BigDecimal(systemPowerEntity.getSpecialPower() * 100);
        }
        map.put("now_power", power.intValue() + "%");
        //HLB冻结数量
        map.put("hlb_frozen", hlbEntity.getFrozenAssets());
        return R.ok(map);
    }

    @Login
    @PostMapping("myPower")
    @ApiOperation("我的算力")
    public R myPower(@RequestAttribute("uid") String uid) {
        UserInfoEntity userInfo = levelFeign.selectUserInfoByUid(uid);
        Map<String, Object> map = new HashMap<>();
        //当前算力
        SystemPowerEntity systemPowerEntity = levelFeign.selectSystemPowerById(userInfo.getUpgradeNum());
        Integer specialPower = Integer.valueOf(levelFeign.getVal("special_level"));
        SystemVipEntity vipEntity = levelFeign.selectSystemVipById(userInfo.getVipLevel());
        SystemPowerEntity powerEntity1 = levelFeign.selectSystemPowerById(vipEntity.getMinPower());
        SystemPowerEntity powerEntity2 = levelFeign.selectSystemPowerById(vipEntity.getMaxPower());
        BigDecimal now_power = new BigDecimal(String.valueOf(systemPowerEntity.getPower())).multiply(new BigDecimal(100));
        BigDecimal min = new BigDecimal(String.valueOf(powerEntity1.getPower())).multiply(new BigDecimal(100));
        BigDecimal max = new BigDecimal(String.valueOf(powerEntity2.getPower())).multiply(new BigDecimal(100));
        if(specialPower.equals(userInfo.getVipLevel())){
            now_power =  new BigDecimal(String.valueOf(systemPowerEntity.getSpecialPower())).multiply(new BigDecimal(100));
            min = new BigDecimal(String.valueOf(powerEntity1.getSpecialPower())).multiply(new BigDecimal(100));
            max = new BigDecimal(String.valueOf(powerEntity2.getSpecialPower())).multiply(new BigDecimal(100));
        }

        map.put("now_power", now_power.intValue() + "%");
        //算力区间
        map.put("min_max",min.intValue() +"% ~ "+max.intValue()+"%");
        //总邀请人数
        UserInvitationCodeEntity code = levelFeign.selectInvitationCodeByUid(uid);
        List<UserRelationEntity> reles = levelFeign.selectRelationByCode(code.getCode());
        map.put("counts", reles.size());
        String point = levelFeign.getVal("power_point");
        //提示
        map.put("point", point);


        return R.ok(map);
    }

    @Login
    @PostMapping("myHLB")
    @ApiOperation("我的HLB")
    public R myHLB(@RequestAttribute("uid") String uid) {
        UserAssetsNpcEntity npcEntity = levelFeign.selectUserAssetsNpcById(uid);
        Map<String, Object> map = new HashMap<>();
        //npc可用
        map.put("npc_available", npcEntity.getAvailableAssets());
        //npc冻结
        map.put("npc_frozen", npcEntity.getFrozenAssets());
        //HLB可用量
        UserAssetsHlbEntity hlbEntity = levelFeign.selectUserAssetsHlbById(uid);
        map.put("hlb_available", hlbEntity.getAvailableAssets());
        //HLB冻结数量
        map.put("hlb_frozen", hlbEntity.getFrozenAssets());
        //页面提示信息
        String point = levelFeign.getVal("hlb_point");
        map.put("point",point);
        //目前额度
        UserInfoEntity userInfo = levelFeign.selectUserInfoByUid(uid);
        SystemVipEntity vipEntity = levelFeign.selectSystemVipById(userInfo.getVipLevel());
        map.put("now_amount",vipEntity.getVipCash());
        List<SystemVipEntity> vipes = levelFeign.selectSystemVipes();
        vipes.remove(0);
        List<Map<String, Object>> maps = new ArrayList<>();
        //npc单价
        String npc = levelFeign.getVal("npc_unit_price");
        //冻结比例
        Float freezeRateNpc = Float.valueOf(levelFeign.getVal("freeze_rate_npc"));
        vipes.stream().forEach(u -> {
            Map<String, Object> vips = new HashMap<>();
            vips.put("amountId",u.getId());
            //前端显示赠送HLB倍数
            vips.put("rate", u.getHlbGiveRate().multiply(new BigDecimal(npc)));
            //档位金额
            vips.put("vip_amount", u.getVipCash());
            //所需npc数量
            Float npcNums = u.getVipCash() / Float.valueOf(npc);
            if ((npcNums * 100) % 100 != 0) {
                npcNums = npcNums+1;
            }
            //需要npc数量
            vips.put("npcNums",npcNums.intValue());
            //赠送hlb数量
            vips.put("hlbNums",u.getHlbAmount());
            //冻结npc数量
            vips.put("npcFrozen",(int)(npcNums.intValue()*freezeRateNpc));
            maps.add(vips);
        });
        map.put("vipMes",maps);
        return R.ok(map);
    }

    @Login
    @PostMapping("getHlbLog")
    @ApiOperation("获取欢乐币赠送记录")
    public R getHlbLog(@RequestAttribute("uid") String uid) {
        List<UserHlbTradeHistoryEntity> list = levelFeign.selectHlbHistorys(uid);
        return R.ok(new ModelMap("historys", list));
    }

    @Login
    @PostMapping("getInviationAdress")
    @ApiOperation("分享邀请地址")
    public R getInviationAdress(@RequestAttribute("uid") String uid) {
        Map<String, Object> map = new HashMap<>();
        UserInvitationCodeEntity code = levelFeign.selectInvitationCodeByUid(uid);
        String address = levelFeign.getPath(4);
        //1.分享地址
        map.put("address", address + code.getCode());
        List<UserInfoBean> relationEntities = levelFeign.selectRelationAndMobileByCode(code.getCode());
        //实际邀请人数
        map.put("realNum", relationEntities.size());
        //真实人数信息
        map.put("realMes", relationEntities);
        List<UserInfoBean> infoEntities = levelFeign.selectUserInfoLikeCode("%" + code.getCode() + "%");
        //总人数
        map.put("allNum", infoEntities.size());
        //总人数信息
        map.put("allMes", infoEntities);
        //判断左区邀请码是否存在
        List<UserInfoEntity> infoEntityList = levelFeign.selectUserInfoesLikeCode("%" + code.getCode() + "%");
        List<UserRelationEntity> userRelationEntities = levelFeign.selectRelationByCode(code.getCode());
        map.put("showRgt", Common.UNAUTHENTICATION);
        infoEntityList.stream().forEach(u -> {
            if (u.getArea().equals("L") && userRelationEntities.size() > 0) {
                map.put("showRgt", Common.AUTHENTICATION);
            }
        });
        return R.ok(map);


    }



}
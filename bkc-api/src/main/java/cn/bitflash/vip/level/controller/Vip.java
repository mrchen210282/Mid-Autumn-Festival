package cn.bitflash.vip.level.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.exception.RRException;
import cn.bitflash.utils.Common;
import cn.bitflash.utils.Encrypt;
import cn.bitflash.utils.R;
import cn.bitflash.vip.level.entity.NpcForm;
import cn.bitflash.vip.level.feign.LevelFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/level")
@Api(value = "提升算力Con", tags = {"加入"})
public class Vip {

    @Autowired
    private LevelFeign levelFeign;

    @Login
    @PostMapping("showVipMess")
    public R showVipMess(@RequestAttribute("uid") String uid) {
        //所有额度信息
        List<SystemVipEntity> vips = levelFeign.selectSystemVipes();
        UserAssetsNpcEntity npcEntity = levelFeign.selectUserAssetsNpcById(uid);
        UserInfoEntity userInfo = levelFeign.selectUserInfoByUid(uid);
        float npc = Float.valueOf(levelFeign.getVal("npc_unit_price"));
        float giveRate = Float.valueOf(levelFeign.getVal("hlb_give_rate"));
        //用户目前额度等级
        int vipLevel = userInfo.getVipLevel();
        //最大额度信息
        SystemVipEntity maxVip = vips.get(vips.size()-1);
        //返回值集合
        Map<String, Object> map = new HashMap<>();
        //额度信息
        map.put("vips", vips);
        //当前累计npc购买金额
        map.put("now_cash", npcEntity.getNpcPrice());
        //下一级额度信息
        //当前额度信息和下一级额度信息
        List<SystemVipEntity> userVips = vips.stream().filter(u -> u.getId() ==vipLevel || u.getId()==vipLevel+1).collect(Collectors.toList());
        if(userVips.size()==1){
            //最大算力
            map.put("now_amount",maxVip.getVipCash());
            map.put("next_amount",maxVip.getVipCash());
            map.put("npc_amount",0);


        }else{
            map.put("now_amount",userVips.get(0).getVipCash());
            map.put("next_amount",userVips.get(1).getVipCash());
            float nextUpnpc =( userVips.get(1).getVipCash()-npcEntity.getNpcPrice())/npc;
            if((nextUpnpc*100)%100==0){
                map.put("npc_amount",(int)nextUpnpc);
            }else{
                map.put("npc_amount",(int)nextUpnpc+1);
            }
        }
        return R.ok(map);

    }

    @Login
    @PostMapping("updateLevel")
    @ApiOperation("提升算力")
    public R updateVipLevel(@RequestAttribute("uid") String uid, @RequestBody NpcForm form) {
        //1.验证 password
        UserSecretEntity secretEntity = levelFeign.selectUserSecretById(uid);
        if (!Encrypt.SHA256(form.getPassword() + secretEntity.getSalt()).equals(secretEntity.getPassword())) {
            return R.error("密码错误");
        }
        //2.验证 邀请码
        UserInfoEntity userInfo = levelFeign.selectUserInfoByUid(uid);
        if (userInfo.getIsInvited().equals(Common.UNAUTHENTICATION) || userInfo.getInvitationCode() == null) {
            return R.ok("没有邀请码用户");
        }
        //3.验证 NPC数量 单价
        UserAssetsNpcEntity npcEntity = levelFeign.selectUserAssetsNpcById(uid);
        //4.赠送比例
        float giveRate = Float.valueOf(levelFeign.getVal("hlb_give_rate"));
        if (npcEntity.getAvailableAssets() < form.getNpc()) {
            return R.error("NPC数量不足");
        }
        //npc单价
        float npc = Float.valueOf(levelFeign.getVal("npc_unit_price"));
        float hlb = npc * form.getNpc() * giveRate;
        if (hlb != form.getHlb()) {
            return R.error("可换取的HLB数量有误");
        }
        //5.添加可冻结hlb和冻结npc数量
        UserAssetsHlbEntity hlbEntity = levelFeign.selectUserAssetsHlbById(uid);
        hlbEntity.setFrozenAssets(hlbEntity.getFrozenAssets() + hlb);
        levelFeign.updateUserAssetsHlb(hlbEntity);
        npcEntity.setAvailableAssets(npcEntity.getAvailableAssets() - form.getNpc());
        npcEntity.setFrozenAssets(npcEntity.getFrozenAssets() + form.getNpc());
        npcEntity.setNpcPrice(npcEntity.getNpcPrice() + (form.getNpc() / npc));
        levelFeign.updateUserAssetsNpc(npcEntity);
        //6.5 加入兑换历史
        UserHlbTradeHistoryEntity hlbTradeHistoryEntity = new UserHlbTradeHistoryEntity();
        hlbTradeHistoryEntity.setId("00" + RandomStringUtils.randomNumeric(6));
        hlbTradeHistoryEntity.setUid(uid);
        hlbTradeHistoryEntity.setTotalHlb(hlb);
        hlbTradeHistoryEntity.setTotalNpc(form.getNpc());
        levelFeign.insertUserHlbTradeHistory(hlbTradeHistoryEntity);

        UserRelationEntity relation = levelFeign.selectRelationByUid(uid);
        String code[] = userInfo.getInvitationCode().split("-");
        String invitCode = code[0];
        String area = code[1];
        UserInvitationCodeEntity pCode = levelFeign.selectInvitationCodeByCode(invitCode);
        //6.验证排点
        if (relation != null) {
            return R.ok();
        } else {
            //7.增加人数
            UserInfoEntity f_info = levelFeign.selectUserInfoByUid(pCode.getUid());
            int peopleNum = f_info.getUpgradeNum();
            //最大算力id
            String power_id = levelFeign.getVal("max_power_id");
            SystemPowerEntity systemPowerEntity = levelFeign.selectSystemPowerById(power_id);
            if (peopleNum < systemPowerEntity.getUpgradeNum()) {
                //邀请人数小于最大算力对应的人数
                f_info.setUpgradeNum(peopleNum + 1);
                levelFeign.updateUserInfo(f_info);
            }

        }

        //8.初始化邀请码
        UserInvitationCodeEntity codeEntity = new UserInvitationCodeEntity();
        codeEntity.setUid(uid);
        codeEntity.setCode(RandomStringUtils.randomAlphanumeric(8).toUpperCase());
        levelFeign.insertInvitation(codeEntity);
        //9.没有排点，进行排点
        List<UserRelationEntity> f_user = levelFeign.selectTreeNodes(pCode.getUid());
        //父类下所有的子类数量（包含父类）
        int size = f_user.size();
        switch (area) {
            case "L":
                if (size == 1) {
                    levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode, "L");
                } else if (size == 2) {
                    levelFeign.insertTreeNode(f_user.get(1).getUid(), uid, invitCode, "L");
                } else if (size > 2) {
                    //筛选出左区第一个子节点
                    UserRelationEntity ue = f_user.stream().filter((u) -> u.getLft() == f_user.get(0).getLft() + 1).findFirst().get();
                    List<UserRelationEntity> child2_user = f_user.stream().filter((u) ->
                            u.getLft() >= ue.getLft() && u.getRgt() <= ue.getRgt()).collect(Collectors.toList());
                    if (child2_user.size() == 1) {
                        levelFeign.insertTreeNode(child2_user.get(0).getUid(), uid, invitCode, "L");
                    } else if (child2_user.size() > 1) {
                        levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitCode, "L");
                    }
                }
                break;
            case "R":
                if (size == 1) {
                    //等于1 = 没有左区，需要先排左区
                    throw new RRException("邀请码不正确");
                }
                //等于2代表直接父类下面开辟中区,或者左区下面只有一个点
                else if (size == 2) {
                    levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode, "R");
                } else if (size > 3) {
                    if (f_user.get(0).getRgt() == f_user.get(1).getRgt() + 1) {
                        //   o 情况1   实现 o
                        //  o             o o
                        // o             o
                        levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode, "R");
                        return R.ok();
                    }
                    //筛选出右区第一个子节点
                    UserRelationEntity ue = f_user.stream().filter((u) -> u.getLft() == f_user.get(1).getRgt() + 1).findFirst().get();
                    List<UserRelationEntity> child2_user = f_user.stream().filter((u) ->
                            u.getLft() >= ue.getLft() && u.getRgt() <= ue.getRgt()).collect(Collectors.toList());
                    if (child2_user.size() == 1) {
                        //    o  情况2  实现 o
                        //   o o           o  o
                        //                   o
                        levelFeign.insertTreeNode(child2_user.get(0).getUid(), uid, invitCode, "R");
                    } else {
                        levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitCode, "R");
                    }
                }
                break;
           /* case "R":
                if (size < 3 || f_user.get(0).getRgt() == f_user.get(1).getRgt() + 1) {
                    //等于2 = 没有左区，中区，需要先排左区和中区
                    throw new RRException("邀请码不正确");
                } else if (size == 3 && f_user.get(0).getRgt() != f_user.get(1).getRgt() + 1) {
                    levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode, "R");
                } else if (size > 3) {

                    UserRelationEntity ue = f_user.stream().filter(u -> f_user.get(1).getRgt() + 1 == u.getLft()).findFirst().get();
                    UserRelationEntity ue2 = f_user.stream().filter(u -> f_user.get(0).getRgt() == u.getRgt() + 1).findFirst().get();
                    if (ue.equals(ue2)) {
                        //    o     情况：不存在右区  实现   o
                        //  o  o                        o  o  o
                        // o                          o
                        levelFeign.insertTreeNode(f_user.get(0).getUid(), uid, invitCode, "R");
                    } else {
                        //     o     情况：不存在右区  实现     o
                        //  o  o  o                       o  o  o
                        // o                                   o

                        List<UserRelationEntity> child2_user = f_user.stream().filter((u) ->
                                u.getLft() >= ue2.getLft() && u.getRgt() <= ue2.getRgt()).collect(Collectors.toList());
                        if(child2_user.size()==1){
                            levelFeign.insertTreeNode(child2_user.get(0).getUid(),uid,invitCode,"R");
                        }else{

                            levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitCode, "R");
                        }
                    }
                }
                break;
        }*/
        }
        return R.ok();
    }

    public String getChildNode(List<UserRelationEntity> p1_user, Map<String, UserRelationEntity> map) {
        /**
         * 寻找收益最高的下面的点
         * 实现原理：递归筛选，直到筛选出末尾节点
         */
        UserRelationEntity last_node = null;
        if (p1_user.size() > 1) {
            //左区比右区收益高/相等 添加到左区
            UserRelationEntity p2_user;
            //左节点
            p2_user = p1_user.stream().filter((u) -> u.getLft() == p1_user.get(0).getLft() + 1).findFirst().get();
            List<UserRelationEntity> p3_user = p1_user.stream().filter((u) -> (
                    u.getLft() >= p2_user.getLft()) && u.getRgt() <= p2_user.getRgt()).collect(Collectors.toList());
            if (p3_user.size() == 1) {
                map.put("key", p3_user.get(0));
            } else {
                this.getChildNode(p3_user, map);
            }
        }
        last_node = map.get("key");
        return last_node.getUid();
    }
}

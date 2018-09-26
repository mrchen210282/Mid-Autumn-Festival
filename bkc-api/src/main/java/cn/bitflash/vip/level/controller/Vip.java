//package cn.bitflash.vip.level.controller;
//
//import cn.bitflash.annotation.Login;
//import cn.bitflash.entity.*;
//import cn.bitflash.exception.RRException;
//import cn.bitflash.utils.Common;
//import cn.bitflash.utils.R;
//import cn.bitflash.vip.level.entity.UserRelationJoinAccountEntity;
//import cn.bitflash.vip.level.feign.LevelFeign;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/level")
//@Api(value = "提升算力Con", tags = {"加入"})
//public class Vip {
//
//    @Autowired
//    private LevelFeign levelFeign;
//
//    @Login
//    @PostMapping("updateLevel")
//    @ApiOperation("提升算力")
//    public R updateVipLevel(@RequestAttribute("uid") String uid) {
//
//        UserInfoEntity userInfo = levelFeign.selectUserInfoByUid(uid);
//        if(userInfo.getIsInvitated().equals(Common.UNAUTHENTICATION) || userInfo.getInvitationCode() ==null){
//            return R.ok("没有邀请码用户");
//        }
//
//        UserCashAssetsEntity cash = levelFeign.selectCashAssetsByUid(uid);
//        List<DictComputingPowerEntity> power = levelFeign.selectComputerPowersById(cash.getLevel(),cash.getLevel()+1);
//        if (power.size() < 2) {
//            return R.error("更高算力暂未开放");
//        }
//        UserDigitalAssetsEntity digitalAssets = levelFeign.selectDigitalAssetsByUid(uid);
//
//        /**
//         * 扣除冻结的bkc
//         * 提升vip  userinfo
//         */
//        float leftcha = power.get(1).getPosition().getLeft() - power.get(0).getPosition().getLeft();
//        float rightcha = power.get(1).getPosition().getRight() - power.get(0).getPosition().getRight();
//        float centercha = power.get(1).getPosition().getCenter() - power.get(0).getPosition().getCenter();
//        float sumcha = leftcha+ rightcha + centercha;
//        if (sumcha > digitalAssets.getAvailable().floatValue()) {
//            return R.error("bkc数量不够");
//        }
//        //扣除可用的bkc
//        digitalAssets.setAvailable(digitalAssets.getAvailable().subtract(new BigDecimal(sumcha)));
//        digitalAssets.setPurchase(new BigDecimal(sumcha).add(digitalAssets.getPurchase()));
//        digitalAssets.setFrozen(digitalAssets.getFrozen().add(new BigDecimal(sumcha)));
//        levelFeign.updateDigitalAssetsByUid(digitalAssets);
//
//        //更新算力
//        cash.setLevel(power.get(1).getLevel());
//        levelFeign.updateUserCashAssetsById(cash);
//        //如果已经排过点了
//        UserRelationEntity relation = levelFeign.selectRelationByUid(uid);
//        if (relation != null ) {
//            return R.ok();
//        }
//
//        //没有排点，进行排点
//        String code[] = userInfo.getInvitationCode().split("-");
//        String invitCode = code[0];
//        String area = code[1];
//        UserInvitationCodeEntity pCode = levelFeign.selectInvitationCodeByCode(invitCode);
//        List<UserRelationEntity> f_user = levelFeign.selectTreeNodes(pCode.getUid());
//        //父类下所有的子类数量（包含父类）
//        int size = f_user.size();
//        switch (area) {
//            case "l":
//                if (size == 1) {
//                    levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode);
//                } else if (size == 2) {
//                    levelFeign.insertTreeNode(f_user.get(1).getUid(), uid, invitCode);
//                } else if (size > 2) {
//                    //筛选出左区第一个子节点
//                    UserRelationJoinAccountEntity ue = f_user.stream().filter((u) -> u.getLft() == f_user.get(0).getLft() + 1).findFirst().get();
//                    List<UserRelationJoinAccountEntity> child2_user = f_user.stream().filter((u) ->
//                            u.getLft() >= ue.getLft() && u.getRgt() <= ue.getRgt()).collect(Collectors.toList());
//                    if (child2_user.size() == 1) {
//                        levelFeign.insertTreeNode(child2_user.get(0).getUid(), uid, invitCode);
//                    } else if (child2_user.size() > 1) {
//                        levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitCode);
//                    }
//                }
//                break;
//            case "c":
//                if (size == 1) {
//                    //等于1 = 没有左区，需要先排左区
//                    throw new RRException("邀请码不正确");
//                }
//                //等于2代表直接父类下面开辟中区,或者左区下面只有一个点
//                else if (size == 2) {
//                    levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode);
//                } else if (size > 3) {
//                    if (f_user.get(0).getRgt() == f_user.get(1).getRgt() + 1) {
//                        //   o 情况1   实现 o
//                        //  o             o o
//                        // o             o
//                        levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode);
//                        return R.ok();
//                    }
//                    //筛选出右区第一个子节点
//                    UserRelationJoinAccountEntity ue = f_user.stream().filter((u) -> u.getLft() == f_user.get(1).getRgt() + 1).findFirst().get();
//                    List<UserRelationJoinAccountEntity> child2_user = f_user.stream().filter((u) ->
//                            u.getLft() >= ue.getLft() && u.getRgt() <= ue.getRgt()).collect(Collectors.toList());
//                    if (child2_user.size() == 1) {
//                        //    o  情况2  实现 o
//                        //   o o           o  o
//                        //                   o
//                        levelFeign.insertTreeNode(child2_user.get(0).getUid(), uid, invitCode);
//                    } else {
//                        levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitCode);
//                    }
//                }
//                break;
//            case "r":
//                if (size < 3 || f_user.get(0).getRgt() == f_user.get(1).getRgt() + 1) {
//                    //等于2 = 没有左区，中区，需要先排左区和中区
//                    throw new RRException("邀请码不正确");
//                } else if (size == 3 && f_user.get(0).getRgt() != f_user.get(1).getRgt() + 1) {
//                    levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode);
//                } else if (size > 3) {
//
//                    UserRelationJoinAccountEntity ue = f_user.stream().filter(u -> f_user.get(1).getRgt() + 1 == u.getLft()).findFirst().get();
//                    UserRelationJoinAccountEntity ue2 = f_user.stream().filter(u -> f_user.get(0).getRgt() == u.getRgt() + 1).findFirst().get();
//                    if (ue.equals(ue2)) {
//                        //    o     情况：不存在右区  实现   o
//                        //  o  o                        o  o  o
//                        // o                          o
//                        levelFeign.insertTreeNode(f_user.get(0).getUid(), uid, invitCode);
//                    } else {
//                        //     o     情况：不存在右区  实现     o
//                        //  o  o  o                       o  o  o
//                        // o                                   o
//                        List<UserRelationJoinAccountEntity> child2_user = f_user.stream().filter((u) ->
//                                u.getLft() >= ue2.getLft() && u.getRgt() <= ue2.getRgt()).collect(Collectors.toList());
//                        levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitCode);
//                    }
//                }
//                break;
//        }
//        return R.ok();
//    }
//
//
//    public String getChildNode(List<UserRelationJoinAccountEntity> p1_user, Map<String, UserRelationJoinAccountEntity> map) {
//        /**
//         * 寻找收益最高的下面的点
//         * 实现原理：递归筛选，直到筛选出末尾节点
//         */
//        UserRelationJoinAccountEntity last_node = null;
//        if (p1_user.size() > 1) {
//            //左区比右区收益高/相等 添加到左区
//            UserRelationJoinAccountEntity p2_user;
//            //左节点
//            p2_user = p1_user.stream().filter((u) -> u.getLft() == p1_user.get(0).getLft() + 1).findFirst().get();
//            List<UserRelationJoinAccountEntity> p3_user = p1_user.stream().filter((u) -> (
//                    u.getLft() >= p2_user.getLft()) && u.getRgt() <= p2_user.getRgt()).collect(Collectors.toList());
//            if (p3_user.size() == 1) {
//                map.put("key", p3_user.get(0));
//            } else {
//                this.getChildNode(p3_user, map);
//            }
//        }
//        last_node = map.get("key");
//        return last_node.getUid();
//    }
//}

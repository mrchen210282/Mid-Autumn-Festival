package cn.bitflash.vip.level.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserInfoConfigEntity;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.util.CodeUtils;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.vip.level.entity.UserRelationJoinAccountEntity;
import cn.bitflash.vip.level.feign.LevelFeign;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/level")
@Api(value = "加入社区Con", tags = {"加入"})
public class Vip {

    @Autowired
    private LevelFeign levelFeign;

    @Login
    @PostMapping("updateVipLevel")
    public R updateVipLevel(@RequestAttribute("uid") String uid, @RequestParam("vipLevel") Integer vipLevel) {
        /**
         *  1.查询是否是vip
         *		只有vip等级为0的用户才能有机会赠送贝壳
         *  2.查询可用资产(availableAssets)是否够用
         *      2.1 金额够升级
         *          2.1.1 可用资产(可用资产=调节释放+调节收益)
         *          2.1.2 购买数量+=20000
         *          2.1.3 赠送数量+=20000*0.05
         *          2.1.4 冻结数量+=20000+赠送数量
         *      2.2 return 金额不足
         */
        UserInfoEntity userInfo = levelFeign.selectUserInfoByUid(uid);
        String invitationCode = userInfo.getInvitationCode();
        if (StringUtils.isBlank(invitationCode)) {
            return R.error("非邀请码注册用户");
        }
        if (!userInfo.getIsVip().equals(Common.VIP_LEVEL_0)) {
            return R.error("后续升级VIP操作请联系后台管理");
        }
        //vip升级消息
        UserInfoConfigEntity vip = levelFeign.selectInfoConfigById(vipLevel);
        //升级vip所需的贝壳数量
        int vip_count = vip.getMin();
        //赠送数量
        Double giveRatio = 0.0;
        if (userInfo.getIsVip().equals(Common.VIP_LEVEL_0)) {
            giveRatio = vip.getMin() * vip.getGiveRate();
        }
        //BigDecimal类型的vip升级数量
        BigDecimal vip_number = new BigDecimal(vip_count);
        UserAccountEntity userAccount = levelFeign.selectAccountByUid(uid);
        BigDecimal acacilNum = userAccount.getAvailableAssets();
        // 可用资产>=所需升级的vip数量
        if (acacilNum.compareTo(vip_number) == 1 || acacilNum.compareTo(vip_number) == 0) {
            /**
             * 计算升级VIP之后的数据
             * 2.1金额够升级vip2
             * 2.1.1 可用资产(可用资产=调节释放+调节收益)-=20000
             * 2.1.2 购买数量+=20000
             * 2.1.3 赠送数量+=20000*0.5
             * 2.1.4 冻结数量+=20000+赠送数量
             */
            BigDecimal zero = new BigDecimal(0.00);
            double result = userAccount.getRegulateRelease().doubleValue() - vip_count;
            if (result <= 0) {
                userAccount.setRegulateRelease(zero);
                userAccount.setRegulateIncome(new BigDecimal(result + userAccount.getRegulateIncome().doubleValue()));
            } else {
                userAccount.setRegulateRelease(new BigDecimal(result));
            }
            //2.1.1
            userAccount.setAvailableAssets(userAccount.getRegulateRelease().add(userAccount.getRegulateIncome()));
            //2.1.2
            userAccount.setPurchase(userAccount.getPurchase().add(vip_number));
            //2.1.3
            userAccount.setGiveAmount(userAccount.getGiveAmount().add(new BigDecimal(giveRatio)));
            //2.1.4
            userAccount.setFrozenAssets(userAccount.getFrozenAssets().add(vip_number.add(new BigDecimal(giveRatio))));
            userAccount.setTotelAssets(userAccount.getPurchase().add(userAccount.getGiveAmount()));
            levelFeign.updateAccountById(userAccount);
            //更新会员等级
            userInfo.setIsVip(vip.getId().toString());
            userInfo.setVipCreateTime(new Date());
            levelFeign.updateUserInfoById(userInfo);
            UserInvitationCodeEntity userInvit = new UserInvitationCodeEntity();
            userInvit.setUid(uid);
            userInvit.setLftCode(CodeUtils.genInvitationCode());
            userInvit.setRgtCode(CodeUtils.genInvitationCode());
            //生成会员的邀请码
            levelFeign.insertInvitationCode(userInvit);
            //插入体系内
            this.insertTradeCodes(invitationCode, uid);
            //logger.info("升级vip的用户uid/手机号为：" + user.getUid() + "--" + user.getMobile());
            return R.ok();
        } else {
            return R.error("资金不足");
        }
    }


    /**
     * @param invitationCode
     * @param uid
     */
    public R insertTradeCodes(String invitationCode, String uid) {
        /**
         * 1.根据邀请码查询出父节点的uid
         * 2.找到父节点的下面的子节点
         * 3.判断左右区邀请码
         *
         * 情况1：o  情况2：o  情况3：o
         *      /         /\       /
         *     o         o  o     o
         *                        /
         *                       o
         *
         */
        UserInvitationCodeEntity pCode = levelFeign.selectInvitationCodeByCode(invitationCode);

        List<UserRelationJoinAccountEntity> f_user = levelFeign.selectTreeNodes(pCode.getUid());
        List<UserRelationJoinAccountEntity> child_user = levelFeign.selectTreeNodes(uid);
        //左区邀请码
        if (invitationCode.equals(pCode.getLftCode()) && child_user.size() == 0 && f_user.size() > 0) {
            if (f_user.size() == 1) {
                levelFeign.insertTreeNode(pCode.getUid(), uid, invitationCode);

            } else if (f_user.size() == 2) {
                levelFeign.insertTreeNode(f_user.get(1).getUid(), uid, invitationCode);
            } else if (f_user.size() > 2) {
                //筛选出左区第一个子节点
                UserRelationJoinAccountEntity ue = f_user.stream().filter((u) -> u.getLft() == f_user.get(0).getLft() + 1).findFirst().get();
                List<UserRelationJoinAccountEntity> child2_user = f_user.stream().filter((u) ->
                        u.getLft() >= ue.getLft() && u.getRgt() <= ue.getRgt()).collect(Collectors.toList());
                if (child2_user.size() == 1) {
                    levelFeign.insertTreeNode(child2_user.get(0).getUid(), uid, invitationCode);
                } else if (child2_user.size() > 1) {
                    levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitationCode);
                }
            }
        } else if (invitationCode.equals(pCode.getRgtCode()) && child_user.size() == 0 && f_user.size() > 0) {
            if (f_user.size() == 1) {
                //return R.error("只能先插入左区" );
                throw new RRException("只能先插入左区！");
            } else if (f_user.size() == 2) {
                levelFeign.insertTreeNode(pCode.getUid(), uid, invitationCode);
            } else if (f_user.size() > 2) {
                if (f_user.get(0).getRgt() == f_user.get(1).getRgt() + 1) {
                    levelFeign.insertTreeNode(pCode.getUid(), uid, invitationCode);
                    return R.ok();
                }
                //筛选出右区第一个子节点
                UserRelationJoinAccountEntity ue = f_user.stream().filter((u) -> u.getLft() == f_user.get(1).getRgt() + 1).findFirst().get();
                List<UserRelationJoinAccountEntity> child2_user = f_user.stream().filter((u) ->
                        u.getLft() >= ue.getLft() && u.getRgt() <= ue.getRgt()).collect(Collectors.toList());
                if (child2_user.size() == 1) {
                    levelFeign.insertTreeNode(child2_user.get(0).getUid(), uid, invitationCode);
                } else {
                    levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitationCode);
                }
            }
        }
        return R.ok();
    }

    public String getChildNode(List<UserRelationJoinAccountEntity> p1_user, Map<String, UserRelationJoinAccountEntity> map) {
        /**
         * 寻找收益最高的下面的点
         * 实现原理：递归筛选，直到筛选出末尾节点
         */
        UserRelationJoinAccountEntity last_node = null;
        if (p1_user.size() > 1) {
            //左区比右区收益高/相等 添加到左区
            UserRelationJoinAccountEntity p2_user;
            //左节点
            p2_user = p1_user.stream().filter((u) -> u.getLft() == p1_user.get(0).getLft() + 1).findFirst().get();
            List<UserRelationJoinAccountEntity> p3_user = p1_user.stream().filter((u) -> (
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

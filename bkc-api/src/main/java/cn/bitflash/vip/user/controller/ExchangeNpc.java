package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.utils.Encrypt;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.entity.NpcForm;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("npc")
public class ExchangeNpc {

    @Autowired
    private UserFeign userFeign;

    @Login
    @PostMapping("exchangeNpc")
    @ApiOperation("兑换npc")
    public R exchangeNpc(@RequestBody NpcForm form, @RequestAttribute("uid") String uid) {

        UserSecretEntity login = userFeign.selectUserLoginByUid(uid);
        if (!Encrypt.SHA256(form.getPassword() + login.getSalt()).equals(login.getPassword())) {
            return R.error("密码验证错误");
        }
        Float npc_unit_price = Float.valueOf(userFeign.getVal("npc_unit_price"));
        Date now = new DateTime().withTimeAtStartOfDay().toDate();
        DailyTotalNpcEntity npcEntity = userFeign.selectDailyTotalNpcEntityById(now);
        //兑换的npc数量
        Float npc = form.getNpc();
        //兑换的hlb数量
        Float hlb = form.getHlb();
        //当前hlb可兑换的npc数量
        Float npcNum = hlb / npc_unit_price;
        if (npcEntity.getTotalNpc() < npc) {
            return R.error("可兑换npc数量不足");
        }
        if (npcNum != npc) {
            return R.error("可兑换hlb数量发生变化，请重新兑换");
        }


        UserNpcEntity userNpcEntity = new UserNpcEntity();
        userNpcEntity.setId("00" + RandomStringUtils.randomNumeric(6));
        userNpcEntity.setUid(uid);
        userNpcEntity.setTotalHlb(hlb);
        userNpcEntity.setTotalNpc(npc);
        userNpcEntity.setCreateTime(new Date());
        userFeign.insertUserNpcEntity(userNpcEntity);
        //扣除hlb
        UserAssetsHlbEntity hlbNumEntity = userFeign.selectUserAssetsHlbById(uid);
        hlbNumEntity.setHlbAssets(hlbNumEntity.getHlbAssets()-form.getHlb());
        userFeign.updateUserAssetsHlb(hlbNumEntity);
        //增加npc数量
        UserAssetsNpcEntity npcNumEntity = userFeign.selectUserAssetsNpcById(uid);
        npcNumEntity.setNpcAssets(npcNumEntity.getNpcAssets()+npc);
        npcNumEntity.setTotelAssets(npcNumEntity.getFrozenAssets()+npc);
        userFeign.updateUserAssetsNpc(npcNumEntity);
        return R.ok();
    }


}

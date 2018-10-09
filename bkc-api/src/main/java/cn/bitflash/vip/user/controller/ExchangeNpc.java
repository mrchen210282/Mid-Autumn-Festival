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
import java.util.HashMap;
import java.util.Map;

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
        Float hlb_handling_fee = Float.valueOf(userFeign.getVal("hlb_handling_fee"));
        //减去手续费后
        Float npcNum = (hlb-hlb*hlb_handling_fee) / npc_unit_price;
        if (npcEntity.getTotalNpc() < npc) {
            return R.error("可兑换npc数量不足");
        }
        if (npcNum != npc) {
            return R.error("可兑换hlb数量发生变化，请重新兑换");
        }


        UserNpcTradeHistoryEntity userNpcTradeHistoryEntity = new UserNpcTradeHistoryEntity();
        userNpcTradeHistoryEntity.setId("00" + RandomStringUtils.randomNumeric(6));
        userNpcTradeHistoryEntity.setUid(uid);
        userNpcTradeHistoryEntity.setTotalHlb(hlb);
        userNpcTradeHistoryEntity.setTotalNpc(npc);
        userNpcTradeHistoryEntity.setCreateTime(new Date());
        userFeign.insertUserNpcEntity(userNpcTradeHistoryEntity);
        //扣除用户hlb
        UserAssetsHlbEntity hlbNumEntity = userFeign.selectUserAssetsHlbById(uid);
        hlbNumEntity.setAvailableAssets(hlbNumEntity.getAvailableAssets() - form.getHlb());
        userFeign.updateUserAssetsHlb(hlbNumEntity);
        //增加用户npc数量
        UserAssetsNpcEntity npcNumEntity = userFeign.selectUserAssetsNpcById(uid);
        npcNumEntity.setAvailableAssets(npcNumEntity.getAvailableAssets() + npc);
        npcNumEntity.setTotelAssets(npcNumEntity.getFrozenAssets() + npc);
        userFeign.updateUserAssetsNpc(npcNumEntity);
        //扣除可兑换npc
        npcEntity.setTotalNpc(npcEntity.getTotalNpc() - npc);
        userFeign.updateDailyTotalNpc(npcEntity);
        return R.ok();
    }

    @Login
    @PostMapping("showNpcNum")
    @ApiOperation("获取npc数量")
    public R showNpcNum(@RequestAttribute("uid") String uid) {
        Date now = new DateTime().withTimeAtStartOfDay().toDate();
        DailyTotalNpcEntity npcEntity = userFeign.selectDailyTotalNpcEntityById(now);
        Map<String, Object> map = new HashMap<>();
        UserAssetsNpcEntity npcNumEntity = userFeign.selectUserAssetsNpcById(uid);
        UserAssetsHlbEntity hlbNumEntity = userFeign.selectUserAssetsHlbById(uid);
        //当前拥有的hlb
        map.put("hlb", hlbNumEntity.getAvailableAssets());
        //当前拥有的npc
        map.put("npc", npcNumEntity.getAvailableAssets());
        //可兑换的npc
        map.put("allNpc", npcEntity.getTotalNpc());
        //100hlb：npc数量
        float npc_unit_price = Float.valueOf(userFeign.getVal("npc_unit_price"));
        int rate = (int) (100 / npc_unit_price);
        map.put("rate", rate);
        return R.ok(map);
    }


}

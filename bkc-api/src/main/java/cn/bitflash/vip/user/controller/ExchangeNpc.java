package cn.bitflash.vip.user.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.utils.Encrypt;
import cn.bitflash.utils.R;
import cn.bitflash.vip.user.entity.NpcForm;
import cn.bitflash.vip.user.feign.UserFeign;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("npc")
public class ExchangeNpc {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        DailyTotalNpcEntity npcEntity = userFeign.selectDailyTotalNpcEntityById();
        //兑换的npc数量
        BigDecimal npc = form.getNpc();
        //兑换的hlb数量
        BigDecimal hlb = form.getHlb();
        //当前hlb可兑换的npc数量
        BigDecimal hlb_handling_fee = new BigDecimal(userFeign.getVal("hlb_handling_fee"));

        if (npcEntity.getRemainderNpc().compareTo(npc) == -1) {
            return R.error("可兑换npc数量不足");
        }

        if (form.getHlb().doubleValue() % 100 != 0) {
            return R.error("HLB兑换数量必须为100的倍数");
        }
        BigDecimal fee = hlb_handling_fee.multiply(form.getHlb());

        if (fee.compareTo(form.getExpense()) != 0) {
            return R.error("手续费出现异常");
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
        hlbNumEntity.setAvailableAssets(hlbNumEntity.getAvailableAssets().subtract(form.getHlb().add(form.getExpense())));
        userFeign.updateUserAssetsHlb(hlbNumEntity);
        //增加用户npc数量
        UserAssetsNpcEntity npcNumEntity = userFeign.selectUserAssetsNpcById(uid);
        npcNumEntity.setAvailableAssets(npcNumEntity.getAvailableAssets().add(npc));
        npcNumEntity.setTotelAssets(npcNumEntity.getTotelAssets().add(npc));
        userFeign.updateUserAssetsNpc(npcNumEntity);
        //扣除可兑换npc
        npcEntity.setRemainderNpc(npcEntity.getRemainderNpc().subtract(npc));
        userFeign.updateDailyTotalNpc(npcEntity);
        return R.ok();
    }

    @Login
    @PostMapping("showNpcNum")
    @ApiOperation("获取npc数量")
    public R showNpcNum(@RequestAttribute("uid") String uid) {
        DailyTotalNpcEntity npcEntity = userFeign.selectDailyTotalNpcEntityById();
        Map<String, Object> map = new HashMap<>();
        UserAssetsNpcEntity npcNumEntity = userFeign.selectUserAssetsNpcById(uid);
        UserAssetsHlbEntity hlbNumEntity = userFeign.selectUserAssetsHlbById(uid);
        //当前拥有的hlb
        map.put("hlb", hlbNumEntity.getAvailableAssets());
        //当前拥有的npc
        map.put("npc", npcNumEntity.getAvailableAssets());
        //可兑换的npc
        map.put("allNpc", npcEntity.getRemainderNpc());
        //100hlb：npc数量
        float npc_unit_price = Float.valueOf(userFeign.getVal("npc_unit_price"));
        int rate = (int) (100 / npc_unit_price);
        map.put("rate", rate);
        //手续费
        String hlb_handling_fee = userFeign.getVal("hlb_handling_fee");
        map.put("hlb_handling_fee", Float.valueOf(hlb_handling_fee));
        return R.ok(map);
    }


    @Login
    @PostMapping("getNpcHistory")
    @ApiOperation("获取npc兑换历史记录")
    public R getNpcHistory(@RequestAttribute("uid") String uid) {
        List<UserNpcTradeHistoryEntity> list = userFeign.selectNpchistory(uid);
        return R.ok(new ModelMap("hostorys", list));
    }


}

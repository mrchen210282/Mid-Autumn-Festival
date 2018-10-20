package cn.bitflash.vip.usersend.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.utils.R;
import cn.bitflash.vip.usersend.bean.UserSendBean;
import cn.bitflash.vip.usersend.feign.SendFrign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "发送Con", tags = {"发送货币"})
public class Send {

    @Autowired
    private SendFrign sendFrign;

    /**
     * @param quantity 发送数量
     * @param user_pwd 交易密码
     * @author 1.查询赠送对象是否存在，若不存在返回code=1错误
     * 2.向user_account表中，扣除发送用户的余额，并向接收者添加余额
     * 3.向user_send表中添加赠送记录
     * 4.向user_brokeage表中添加手续费记录
     */
    @Login
    @PostMapping("send")
    public R userSend(@RequestParam("quantity") String quantity, @RequestParam("address") String address, @RequestParam("user_pwd") String user_pwd, @RequestAttribute("uid") String uid) {

        //交易状态：‘-1’余额不足错误；‘0’操作成功；‘1’用户不存在；‘2’其他错误；‘3’交易数量错误；‘4’交易密码错误
        /*-------------校验---------------*/
        //用户是否存在
        UserWalletAddressEntity sendee = sendFrign.selectAddress(address);
        if (sendee == null || "".equals(sendee)) {
            //用户不存在
            return R.error("用户不存在");
        }

        //交易密码
        UserSecretEntity userSecretEntity = sendFrign.selectSecretById(uid);
        if (!user_pwd.equals(userSecretEntity.getPayPassword())) {
            // 交易密码不正确
            return R.error("交易密码不正确");
        }

        //交易数量是否大于100且是100的倍数
        BigDecimal quantite = new BigDecimal(quantity);
        BigDecimal num = quantite.divide(new BigDecimal(100), 0, BigDecimal.ROUND_DOWN);
        BigDecimal result = num.subtract(quantite.divide(new BigDecimal(100)));
        if (quantite.compareTo(new BigDecimal(100)) == -1 || result.compareTo(new BigDecimal(0)) == -1) {
            return R.error("余额不足");
        }

        //赠送数量
        BigDecimal quantity_f = new BigDecimal(quantity);
        BigDecimal trade_quantity = new BigDecimal(quantity);

        //2.5%手续费
        UserMarketConfigEntity userMarketConfigEntity = sendFrign.selectConfigById(2);
        BigDecimal poundage = userMarketConfigEntity.getPoundage();
        BigDecimal user_brokerage = quantity_f.multiply(poundage);


        /*--------在user_assets_npc中修改--------*/
        UserAssetsNpcEntity send_account = sendFrign.selectAssetsById(uid);

        //扣款数量=交易数量+手续费
        BigDecimal deduction_quantity = trade_quantity.add(user_brokerage);
        //可用余额
        BigDecimal npcAssets = send_account.getAvailableAssets();

        //发送人账户扣款
        if (deduction_quantity.compareTo(npcAssets) == -1 || deduction_quantity.compareTo(npcAssets) == 0) {
            send_account.setAvailableAssets(npcAssets.subtract(deduction_quantity));
            sendFrign.updateAssetsById(send_account);
        } else {
            //数量不够扣款
            return R.error("余额不足");
        }

        //接收人账户充值
        UserAssetsNpcEntity sendee_account = sendFrign.selectAssetsById(sendee.getUid());
        sendee_account.setAvailableAssets(sendee_account.getAvailableAssets().add(trade_quantity));
        sendee_account.setTotelAssets(sendee_account.getTotelAssets().add(trade_quantity));
        //更新数据
        sendFrign.updateAssetsById(sendee_account);

        //添加数据user_send
        UserSendEntity us = new UserSendEntity();
        us.setQuantity(quantity_f);
        Date day = new Date();
        us.setSendTime(day);
        us.setSendUid(uid);
        us.setSendeeUid(sendee.getUid());
        sendFrign.insertUserSend(us);

        //UserBrokerage
        UserBrokerageEntity userbroker = sendFrign.selectBrokerage(1);
        BigDecimal get_brokerages = user_brokerage.add(userbroker.getSellBrokerage());

        userbroker.setSellBrokerage(get_brokerages);
        //修改到user_brokerage
        sendFrign.updateBrokerage(userbroker);

        return R.ok();
    }

    @Login
    @PostMapping("send/record")
    public R record(@RequestAttribute("uid") String uid, @RequestParam int state, @RequestParam("pages") String pages) {

        //state = 1 :发送
        if (state == 1) {
            List<UserSendBean> usersendList = sendFrign.selectAccount(uid, Integer.valueOf(pages));
            Integer count = sendFrign.selectAccountCount(uid);
            return R.ok().put("userSendList", usersendList).put("count", count);
        }
        //state = 2 :接收
        else if (state == 2) {
            List<UserSendBean> useracceptList = sendFrign.selectAccept(uid, Integer.valueOf(pages));
            Integer count = sendFrign.selectAcceptCount(uid);
            return R.ok().put("userAcceptList", useracceptList).put("count", count);
        }
        return R.ok();
    }

    @Login
    @PostMapping("send/secretInfo")
    public R secretInfo(@RequestAttribute("uid") String uid) {
        //钱包地址
        String address = sendFrign.selectAddressById(uid).getAddress();
        //可用余额
        BigDecimal npcAssets = sendFrign.selectAssetsById(uid).getAvailableAssets();
        //手续费
        BigDecimal poundage = sendFrign.selectConfigById(2).getPoundage();
        BigDecimal broker = new BigDecimal(100).multiply(poundage);
        BigDecimal brokerage = broker.setScale(2, RoundingMode.HALF_UP);
        return R.ok().put("npcAssets",npcAssets).put("address",address).put("brokerage",brokerage);
    }


}

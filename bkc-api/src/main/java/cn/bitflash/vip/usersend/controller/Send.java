package cn.bitflash.vip.usersend.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.utils.R;
import cn.bitflash.vip.trade.entity.UserTradeConfigEntity;
import cn.bitflash.vip.usersend.feign.SendFrign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("send")
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
    @PostMapping("userSend")
    public R userSend(@RequestParam("quantity") String quantity, @RequestParam("address") String address, @RequestParam("pwd") String user_pwd, @RequestAttribute("uid") String uid) {

        //交易状态：‘-1’余额不足错误；‘0’操作成功；‘1’用户不存在；‘2’其他错误；‘3’交易数量错误；‘4’交易密码错误
        int code = 2;
        //业务状态
        boolean relation = false;

        //如果用户不存在,返回‘用户不存在’错误。
        UserWalletAddressEntity sendee = sendFrign.selectAddress(address);
        if (sendee == null || "".equals(sendee)) {
            //用户不存在
            code = 1;
            return R.ok().put("code", code);
        }

        //如果交易密码不正确,返回错误
        UserSecretEntity userSecretEntity = sendFrign.selectSecretById(uid);
        if (!user_pwd.equals(userSecretEntity.getPayPassword())) {
            // 交易密码不正确
            code = 4;
            return R.ok().put("code", code);
        }

        BigDecimal quantite = new BigDecimal(quantity);
        BigDecimal num = quantite.divide(new BigDecimal(100), 0, BigDecimal.ROUND_DOWN);
        BigDecimal result = num.subtract(quantite.divide(new BigDecimal(100)));
        if (quantite.compareTo(new BigDecimal(100)) == -1 || result.compareTo(new BigDecimal(0)) == -1) {
            code = 3;
            return R.ok().put("code", code);
        }
        //赠送数量
        //String 转换成 float
        DecimalFormat df = new DecimalFormat("#########.##");
        Float quantitys = Float.parseFloat(quantity);
        //赠送数量转换成BigDecimal
        BigDecimal user_quantity = new BigDecimal(quantitys);

        //手续费
        UserMarketConfigEntity userMarketConfigEntity = sendFrign.selectConfigById(2);
        Float poundage = userMarketConfigEntity.getPoundage();


        //2.5%手续费
        BigDecimal user_brokerage = new BigDecimal(quantitys * poundage);

        //Send在user——account中修改
        UserAssetsNpcEntity send_account = sendFrign.selectAssetsById(uid);
        //扣款数量
        BigDecimal user_quantitys = user_quantity.add(user_brokerage);
        BigDecimal npcAssets = new BigDecimal(send_account.getAvailableAssets());
        //账号总额大于扣款
        if (user_quantitys.compareTo(npcAssets) == -1 || user_quantitys.compareTo(npcAssets) == 0) {
            send_account.setAvailableAssets(npcAssets.subtract(user_quantitys).floatValue());
            sendFrign.updateAssetsById(send_account);
            relation = true;

            if (relation) {
                UserAssetsNpcEntity sendee_account = sendFrign.selectAssetsById(sendee.getUid());
                BigDecimal sendeeAssets = new BigDecimal(send_account.getAvailableAssets());
                sendee_account.setNpcAssets(sendeeAssets.add(user_quantity).floatValue());
                //更新数据
                sendFrign.updateAssetsById(sendee_account);
            }
        }else {
            //数量不够扣款
            code = -1;
            //交易失败
            return R.ok().put("code", code);
        }

//        if (user_quantitys.compareTo(sendAccount.getAvailableAssets()) == -1 || user_quantitys.compareTo(sendAccount.getAvailableAssets()) == 0) {
//
//            //如果regulateRelease数量足够扣款
//            if (user_quantitys.compareTo(sendAccount.getRegulateRelease()) == -1 || user_quantitys.compareTo(sendAccount.getRegulateRelease()) == 0) {
//                BigDecimal regulateRelease = sendAccount.getRegulateRelease().subtract(user_quantitys);
//                BigDecimal availableAssets = sendAccount.getAvailableAssets().subtract(user_quantitys);
//                sendAccount.setRegulateRelease(regulateRelease);
//                sendAccount.setAvailableAssets(availableAssets);
//                //更新数据
//                sendFrign.updateAccountById(sendAccount);
//                relation = true;
//            } else {//如果regulateRelease数量不足够扣款
//                //查询regulate_income
//                BigDecimal surplus = user_quantitys.subtract(sendAccount.getRegulateRelease());
//                BigDecimal regulate_income = sendAccount.getRegulateIncome().subtract(surplus);
//                BigDecimal availableAssets = sendAccount.getAvailableAssets().subtract(user_quantitys);
//                sendAccount.setRegulateRelease(new BigDecimal(0.00));
//                sendAccount.setAvailableAssets(availableAssets);
//                sendAccount.setRegulateIncome(regulate_income);
//                //更新数据
//                sendFrign.updateAccountById(sendAccount);
//                relation = true;
//            }
            //Sendee在user——account中修改
//            if (relation) {
//                UserAccountEntity sendee_account = sendFrign.selectAccountByUid(sendee.getUid());
//
//                BigDecimal regulate_income = sendee_account.getRegulateIncome().add(user_quantity);
//                BigDecimal availableAssets = sendee_account.getAvailableAssets().add(user_quantity);
//                sendee_account.setRegulateIncome(regulate_income);
//                sendee_account.setAvailableAssets(availableAssets);
//                //更新数据
//                sendFrign.updateAccountById(sendee_account);
//            }

//        } else {
//            //数量不够扣款
//            code = -1;
//            //交易失败
//            return R.ok().put("code", code);
//        }

        //添加数据user_send,添加赠送记录
        if (relation) {
            UserSendEntity us = new UserSendEntity();
            us.setQuantity(quantitys);
            Date day = new Date();
            us.setSendTime(day);
            us.setSendUid(uid);
            us.setSendeeUid(sendee.getUid());
            sendFrign.insertUserSend(us);
        }

        //如果双方交易完成
        if (relation) {
            UserBrokerageEntity userbroker = sendFrign.selectBrokerage(1);
            BigDecimal get_brokerages = user_brokerage.add(userbroker.getSellBrokerage());
            userbroker.setSellBrokerage(get_brokerages);
            //修改到user_brokerage
            sendFrign.updateBrokerage(userbroker);
            code = 0;
        }
        return R.ok().put("code", code);
    }

    @Login
    @PostMapping("record")
    public R record(@RequestAttribute("uid") String uid, @RequestParam int state, @RequestParam("pages") String pages) {

        //state = 1 :发送
        if (state == 1) {
            List<UserSendEntity> usersendList = sendFrign.selectSendList(uid, Integer.valueOf(pages));
            Integer count = sendFrign.selectSendcount(uid);
            return R.ok().put("usersendList", usersendList).put("count", count);
        }
        //state = 2 :接收
        else if (state == 2) {
            List<UserSendEntity> useracceptList = sendFrign.selectSendList(uid, Integer.valueOf(pages));
            Integer count = sendFrign.selectSendcount(uid);
            return R.ok().put("useracceptList", useracceptList).put("count", count);
        }
        return R.ok();
    }

//    /**
//     * 查看手续费
//     *
//     * @return 发送手续费
//     */
//    @PostMapping("handingFee")
//    public R handingFee() {
//        //手续费
//        UserTradeConfigEntity userTradeConfig = sendFrign.selectTradeConfigById(1);
//        Float poundage = userTradeConfig.getPoundage();
//        return R.ok().put("poundage", poundage);
//    }
}

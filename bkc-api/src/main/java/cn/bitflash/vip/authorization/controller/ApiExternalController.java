package cn.bitflash.vip.authorization.controller;

import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.authorization.common.Common;
import cn.bitflash.vip.authorization.entity.GameAccountHistoryEntity;
import cn.bitflash.vip.authorization.feign.AuthorityFeign;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/external")
public class ApiExternalController {

    private Logger logger = LoggerFactory.getLogger(ApiExternalController.class);

    @Autowired
    private AuthorityFeign authorityFeign;

    /**
     * @param
     * @return count 贝壳数量
     */
    // @OtherLogin
    @GetMapping("getBKCNum")
    public R getBKCNum(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        String apiKey = "b1gtuVZRWVh0BdBX";

        List<Object> inParam = new ArrayList<Object>();
        inParam.add(uid);
        inParam.add(time);
        inParam.add(apiKey);

        String mySign = Common.returnMD5(inParam);

        logger.info("time:" + time);
        logger.info("uid:" + uid);
        if (sign.equals(mySign)) {
            if (StringUtils.isNotBlank(time) && StringUtils.isNotBlank(uid)) {

                UserAssetsNpcEntity userAssetsNpcEntity = authorityFeign.selectUserAssetsNpcById(uid);

                if (null != userAssetsNpcEntity) {
                    String count = userAssetsNpcEntity.getAvailableAssets().toString();
                    Long timeVal = System.currentTimeMillis();
                    List<Object> outParam = new ArrayList<Object>();
                    outParam.add(count);
                    outParam.add(timeVal.toString());
                    outParam.add(apiKey);
                    String returnSign = Common.returnMD5(outParam);

                    logger.info("availableAssets:" + count);

                    return R.ok().put("availableAssets", count.toString()).put("code", 1).put("time", timeVal.toString()).put("sign", returnSign);
                } else {
                    return R.error().put("code", "500");
                }
            }
        }
        return R.error().put("code", "500");
    }


    /**
     * @return
     */
    // @OtherLogin
    @GetMapping("changeBKCNum")
    public R changeBKCNum(HttpServletRequest request) {
        // String uid = token.getUid();
        logger.info("---------------changeBKCNum----------------");
        String uid = request.getParameter("uid");
        String flag = request.getParameter("flag");
        String bkcNum = request.getParameter("bkcNum");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        String apiKey = "b1gtuVZRWVh0BdBX";

        List<Object> inParam = new ArrayList<Object>();
        inParam.add(uid);
        inParam.add(flag);
        inParam.add(bkcNum);
        inParam.add(time);
        inParam.add(apiKey);

        String mySign = Common.returnMD5(inParam);

        logger.info("uid:" + uid);
        logger.info("flag:" + flag);
        logger.info("bkcNum:" + bkcNum);
        logger.info("time:" + time);
        logger.info("sign:" + sign);

        if (sign.equals(mySign)) {
            if (StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(bkcNum) && StringUtils.isNotBlank(flag)) {

                try {
                    if (Integer.valueOf(bkcNum) > 0) {
                        // 解密uid
                        UserAssetsNpcEntity userAssetsNpcEntity = authorityFeign.selectUserAssetsNpcById(uid);

//					UserAccountGameEntity accountGameEntity = userAccountGameService.selectById(uid);
                        if (null != userAssetsNpcEntity) {
                            BigDecimal regulateIncome = new BigDecimal(bkcNum);
                            //添加游戏记录
                            GameAccountHistoryEntity gameAccountHistoryEntity = new GameAccountHistoryEntity();
                            if (flag.equals("0")) {
                                // 加法
                                userAssetsNpcEntity.setAvailableAssets(userAssetsNpcEntity.getAvailableAssets().add(regulateIncome));
                            } else {
                                // 减法
                                if (userAssetsNpcEntity.getAvailableAssets().compareTo(regulateIncome) <= 0) {
                                    return R.error().put("code", "500");
                                } else {
                                    userAssetsNpcEntity.setAvailableAssets(userAssetsNpcEntity.getAvailableAssets().subtract(regulateIncome));
                                }
                            }
//                            accountEntity.setAvailableAssets(accountEntity.getRegulateIncome().add(accountEntity.getRegulateRelease()));
//                            userAccountService.updateById(accountEntity);

                            authorityFeign.updateUserAssetsNpc(userAssetsNpcEntity);

                            //添加游戏贝壳数
                            gameAccountHistoryEntity.setFlag(flag);
                            gameAccountHistoryEntity.setUid(userAssetsNpcEntity.getUid());
                            gameAccountHistoryEntity.setCreateTime(new Date());
                            gameAccountHistoryEntity.setQuantity(regulateIncome);
                            authorityFeign.insertGameAccount(gameAccountHistoryEntity);

                            logger.info("availableAssets:" + userAssetsNpcEntity.getAvailableAssets().toString());
                            Long timeVal = System.currentTimeMillis();
                            List<Object> outParam = new ArrayList<Object>();
                            outParam.add(userAssetsNpcEntity.getAvailableAssets());
                            outParam.add(timeVal.toString());
                            outParam.add(apiKey);
                            String returnSign = Common.returnMD5(outParam);

                            return R.ok().put("code", 1).put("availableAssets", userAssetsNpcEntity.getAvailableAssets().toString()).put("time", timeVal).put("sign", returnSign);
                        } else {
                            logger.info("查询不到用户信息！");
                        }
                    } else {
                        logger.info("贝壳数量为:" + bkcNum);
                    }
                } catch (Exception ex) {
                    logger.info("贝壳数量为:" + bkcNum + ",转换为int值失败！");
                    ex.printStackTrace();
                    return R.error().put("code", "500");
                }
            } else {
                logger.info("密钥错误！");
            }
        }
        return R.error().put("code", "500");
    }
}

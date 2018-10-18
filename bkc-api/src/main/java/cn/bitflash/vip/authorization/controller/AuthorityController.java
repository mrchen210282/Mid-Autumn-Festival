package cn.bitflash.vip.authorization.controller;

import cn.bitflash.entity.UserAssetsNpcEntity;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserSecretEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.authorization.common.Common;
import cn.bitflash.vip.authorization.entity.AuthorityUserEntity;
import cn.bitflash.vip.authorization.entity.UserEmpowerEntity;
import cn.bitflash.vip.authorization.feign.AuthorityFeign;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reg")
public class AuthorityController {

    private Logger logger = LoggerFactory.getLogger(AuthorityController.class);

    @Autowired
    private AuthorityFeign authorityFeign;

    /**
     * @param
     */
    @GetMapping("authorityValidate")
    public R authorityValidate(HttpServletRequest request) {
        logger.info("----校验第三方登录------");

        String appid = request.getParameter("clientid");
        String mobile = request.getParameter("mobile");
        String ticket = request.getParameter("ticket");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        String apiKey = "b1gtuVZRWVh0BdBX";

        logger.info(appid);
        logger.info(mobile);
        logger.info(ticket);
        logger.info(time);
        logger.info(sign);

        List<Object> inParam = new ArrayList<Object>();
        inParam.add(mobile);
        inParam.add(ticket);
        inParam.add(appid);
        inParam.add(time);
        inParam.add(apiKey);

        String mySign = Common.returnMD5(inParam);

        if (sign.equals(mySign)) {
            if (StringUtils.isNotBlank(appid) && StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(ticket)) {
                UserEmpowerEntity userEmpowerEntity = authorityFeign.selectUserEmpowerById(appid,ticket);

                logger.info("appid:" + appid);

                if (null != userEmpowerEntity) {
                    AuthorityUserEntity authorityUserEntity = authorityFeign.selectAuthorityByMobile(mobile);
                    if (null != authorityUserEntity) {

                        BigDecimal availableAssets = null;
                        //UserAccountEntity userAccountEntity = userAccountService.selectOne(new EntityWrapper<UserAccountEntity>().eq("uid", authorityUserEntity.getUid()));
                        Map<String, Object> map = new HashMap<String, Object>();
                        UserAssetsNpcEntity userAssetsNpcEntity = authorityFeign.selectUserAssetsNpcById(authorityUserEntity.getUid());
                        UserInfoEntity userInfoEntity = authorityFeign.selectById(authorityUserEntity.getUid());


                        if (null != userAssetsNpcEntity) {
                            availableAssets = userAssetsNpcEntity.getAvailableAssets();
                        } else {
                            availableAssets = new BigDecimal("0.00");
                        }

                        Long timeVal = System.currentTimeMillis();
                        List<Object> outParam = new ArrayList<Object>();

                        String nickname = "";
                        if (null != userInfoEntity) {
                            nickname = userInfoEntity.getNickname();
                        }

//						Integer intAvailableAssets = new Integer(availableAssets.toString());
                        //返回token信息

                        UserSecretEntity userSecretEntity = authorityFeign.selectByMobile(mobile);
                        outParam.add(userSecretEntity.getToken());
                        outParam.add(authorityUserEntity.getUid());
                        outParam.add(availableAssets);
                        outParam.add(nickname);
                        outParam.add(timeVal.toString());
                        outParam.add(apiKey);
                        String returnSign = Common.returnMD5(outParam);

                        logger.info("availableAssets:" + availableAssets.toString());
                        logger.info("uid:" + authorityUserEntity.getUid());
                        logger.info("nickname:" + nickname);

                        String urlStr = "";
                        try {
                            urlStr = URLEncoder.encode(nickname, "UTF-8");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return R.ok().put("token", userSecretEntity.getToken()).put("uid", authorityUserEntity.getUid()).put("availableAssets", availableAssets.toString()).put("sign", returnSign).put("nickname", urlStr);
                    } else {
                        return R.error();
                    }
                }
            }
        } else {
            logger.info("密钥错误！");
        }
        return R.error();
    }



}

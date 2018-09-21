/*
package cn.bitflash.vip.oauth2.controller;

import cn.bitflash.entity.UserAccountEntity;
import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.vip.oauth2.feign.Oauth2Feign;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/external")
@Api(value = "对外接口")
public class Oauth2 {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Oauth2Feign oauth2Feign;

    */
/**
     * @return count 贝壳数量
     * @throws UnsupportedEncodingException
     *//*

    @ResponseBody
    @GetMapping("getBKCNum")
    public R getBKCNum(HttpServletRequest request) throws UnsupportedEncodingException {
        String uid = request.getParameter("uid");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        String apiKey = "b1gtuVZRWVh0BdBX";

        List<String> inParam = new ArrayList<String>();
        inParam.add(uid);
        inParam.add(time);
        inParam.add(apiKey);

        String mySign = Common.returnMD5(inParam);
        logger.info("time:" + time);
        logger.info("uid:" + uid);
        if (sign.equals(mySign)) {
            if (StringUtils.isNotBlank(time) && StringUtils.isNotBlank(uid)) {
                UserAccountEntity accountEntity = oauth2Feign.selectAccountByUid(uid);
                if (null != accountEntity) {
                    String count = accountEntity.getAvailableAssets().toString();

                    Long timeVal = System.currentTimeMillis();
                    List<String> outParam = new ArrayList<String>();
                    outParam.add(count);
                    outParam.add(timeVal.toString());
                    outParam.add(apiKey);
                    String returnSign = Common.returnMD5(outParam);

                    return R.ok().put("availableAssets", count).put("code", 1).put("time", timeVal.toString()).put("sign", returnSign);
                } else {
                    return R.error().put("code", "500");
                }
            }
        }

        return R.error().put("code", "500");

    }

    */
/**
     * @return
     * @throws UnsupportedEncodingException
     *//*

    @ResponseBody
    // @OtherLogin
    @GetMapping("changeBKCNum")
    public R changeBKCNum(HttpServletRequest request) throws UnsupportedEncodingException {
        // String uid = token.getUid();
        logger.info("---------------changeBKCNum----------------");
        String uid = request.getParameter("uid");
        String flag = request.getParameter("flag");
        String bkcNum = request.getParameter("bkcNum");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        String apiKey = "b1gtuVZRWVh0BdBX";

        List<String> inParam = new ArrayList<String>();
        inParam.add(uid);
        inParam.add(flag);
        inParam.add(bkcNum);
        inParam.add(time);
        inParam.add(apiKey);

        String mySign = Common.returnMD5(inParam);

        logger.info("uid" + uid);
        logger.info("flag:" + flag);
        logger.info("bkcNum:" + bkcNum);
        logger.info("time:" + time);
        logger.info("sign:" + sign);

        if (sign.equals(mySign)) {
            if (StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(bkcNum) && StringUtils.isNotBlank(flag)) {

                UserAccountEntity accountEntity = oauth2Feign.selectAccountByUid(uid);
                if (null != accountEntity) {
                    BigDecimal regulateIncome = new BigDecimal(bkcNum);
                    if (flag.equals("0")) {
                        // 加法
                        accountEntity.setRegulateIncome(accountEntity.getRegulateIncome().add(regulateIncome));
                    } else {
                        // 减法
                        if (accountEntity.getAvailableAssets().compareTo(regulateIncome) <= 0) {
                            return R.error().put("code", "500");
                        } else {
                            accountEntity.setRegulateIncome(accountEntity.getRegulateIncome().subtract(regulateIncome));
                        }
                    }
                    accountEntity.setAvailableAssets(accountEntity.getRegulateIncome().add(accountEntity.getRegulateRelease()));
                    oauth2Feign.updateAccountById(accountEntity);

                    Long timeVal = System.currentTimeMillis();
                    List<String> outParam = new ArrayList<String>();
                    outParam.add(accountEntity.getAvailableAssets().toString());
                    outParam.add(timeVal.toString());
                    outParam.add(apiKey);
                    String returnSign = Common.returnMD5(outParam);
                    return R.ok().put("code", 1).put("availableAssets", accountEntity.getAvailableAssets().toString()).put("time", timeVal).put("sign", returnSign);
                }
            }
        }
        return R.error().put("code", "500");
    }
    @GetMapping("authorityValidate")
    public cn.bitflash.utils.R authorityValidate(HttpServletRequest request) {
        logger.info("----校验第三方登录------");

        String clientid = request.getParameter("clientid");
        System.out.println(clientid);
        String mobile = request.getParameter("mobile");
        String ticket = request.getParameter("ticket");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        String apiKey = "b1gtuVZRWVh0BdBX";

        logger.info(mobile);
        logger.info(ticket);
        logger.info(time);
        logger.info(sign);

        List<String> inParam = new ArrayList<String>();
        inParam.add(mobile);
        inParam.add(ticket);
        inParam.add(clientid);
        inParam.add(time);
        inParam.add(apiKey);

        String mySign = Common.returnMD5(inParam);

        if(sign.equals(mySign)) {
            if(StringUtils.isNotBlank(clientid) && StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(ticket)) {
                UserEmpowerEntity userEmpowerEntity = userEmpowerService.selectOne(new EntityWrapper<UserEmpowerEntity>().eq("appid", clientid).eq("ticket", ticket));
                logger.info("clientid:" + clientid);

                if(null != userEmpowerEntity) {
                    AuthorityUserEntity authorityUserEntity = authorityUserService.selectOne(new EntityWrapper<AuthorityUserEntity>().eq("mobile", mobile));
                    if(null != authorityUserEntity) {

                        BigDecimal availableAssets = null;
                        //UserAccountEntity userAccountEntity = userAccountService.selectOne(new EntityWrapper<UserAccountEntity>().eq("uid", authorityUserEntity.getUid()));
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("uid",authorityUserEntity.getUid());
                        UserAccountEntity userAccountEntity = tradeUtils.selectOne(map);
                        UserInfoEntity userInfoEntity =  userUtils.selectUserInfoById(authorityUserEntity.getUid());

                        if(null != userAccountEntity) {
                            availableAssets = userAccountEntity.getAvailableAssets();
                        } else {
                            availableAssets = new BigDecimal("0.00");
                        }

                        Long timeVal = System.currentTimeMillis();
                        List<String> outParam = new ArrayList<String>();

                        String nickname = "";
                        if(null != userInfoEntity) {
                            nickname = userInfoEntity.getNickname();
                        }

                        //返回token信息
                        TokenEntity tokenEntity = tokenService.selectOne(new EntityWrapper<TokenEntity>().eq("mobile", mobile));
                        outParam.add(tokenEntity.getToken());
                        outParam.add(authorityUserEntity.getUid());
                        outParam.add(availableAssets.toEngineeringString());
                        outParam.add(nickname);
                        outParam.add(timeVal.toString());
                        outParam.add(apiKey);
                        String returnSign = Common.returnMD5(outParam);

                        return cn.bitflash.utils.R.ok().put("token", tokenEntity.getToken()).put("uid", authorityUserEntity.getUid()).put("availableAssets", availableAssets).put("sign", returnSign).put("nickname", nickname);
                    } else {
                        return cn.bitflash.utils.R.error();
                    }
                }
            }
        }
        return cn.bitflash.utils.R.error();
    }

    @GetMapping("/responseCode")
    @ResponseBody
    public cn.bitflash.utils.R toShowUser(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        logger.info("----------服务端/responseCode--------------------------------------------------------------");

        try {
            // 构建OAuth授权请求
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            String clientid = oauthRequest.getClientId();
            //String responseType = oauthRequest.getResponseType();
            String url = oauthRequest.getRedirectURI();
            //String clientSecret = request.getParameter("clientSecret");

            //String redirectUrl = request.getParameter("redirectUrl");

            String mobile = request.getParameter("mobile");
            // clientid = request.getParameter("appid");
            // responseType = request.getParameter("responseType");

            logger.info("回调地址为：" + url);
            logger.info("clientid：" + clientid);

            UserEmpowerEntity userEmpowerEntity = userEmpowerService.selectOne(new EntityWrapper<UserEmpowerEntity>().eq("appid", clientid));
            if (null != userEmpowerEntity) {
                // 判断客户端id和
                // if (clientid.equals("bkc") && responseType.equals("code")) {

                // 设置授权码
                // String authorizationCode = "bkcCode";

                Map<String,Object> map = new HashMap<String,Object>();
                map.put("mobile",mobile);
                List<UserInfoEntity> list = userUtils.selectUserInfoList(map);

                if(null != list) {
                    UserInfoEntity userInfoEntity = list.get(0);
                    AuthorityUserEntity AuthorityUser = authorityUserService.selectOne(new EntityWrapper<AuthorityUserEntity>().eq("uid", userInfoEntity.getUid()));
                    if(null == AuthorityUser) {
                        AuthorityUserEntity authorityUserEntity = new AuthorityUserEntity();
                        authorityUserEntity.setMobile(mobile);
                        authorityUserEntity.setTicket(userEmpowerEntity.getTicket());
                        authorityUserEntity.setUid(userInfoEntity.getUid());
                        authorityUserService.insert(authorityUserEntity);
                    }

                    // 进行OAuth响应构建
                    OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
                    // 设置授权码
                    builder.setCode(userEmpowerEntity.getTicket());
                    // 设置授权有效时间
                    builder.setExpiresIn(1000 * 60 * 60L);
                    // 得到到客户端重定向地址

                    String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);

                    // 构建响应
                    final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
                    logger.info("服务端/responseCode内，返回的回调路径：" + response.getLocationUri());
                    String responceUri = response.getLocationUri();
                    // 根据OAuthResponse返回ResponseEntity响应
                    HttpHeaders headers = new HttpHeaders();
                    try {
                        headers.setLocation(new URI(response.getLocationUri()));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                    Long time = System.currentTimeMillis();
                    String API_KEY = "b1gtuVZRWVh0BdBX";

                    StringBuffer buf = new StringBuffer();
                    buf.append(mobile);
                    buf.append(userEmpowerEntity.getTicket());
                    buf.append(clientid);
                    buf.append(time);
                    buf.append(API_KEY);
                    String sign = new ExternalMD5(buf.toString()).asHex();
                    map.clear();
                    map.put("mobile", mobile);
                    map.put("ticket", userEmpowerEntity.getTicket());
                    map.put("clientid", clientid);
                    map.put("time", time);
                    logger.info("mobile:" + mobile);
                    logger.info("ticket:" + userEmpowerEntity.getTicket());
                    logger.info("clientid:" + clientid);
                    logger.info("time:" + time);
                    return cn.bitflash.utils.R.ok().put("authorityMap", map).put("code", "0").put("sign", sign);
                }
            } else {
                return cn.bitflash.utils.R.error().put("code", "500");
            }

        } catch (OAuthSystemException e) {
            e.printStackTrace();
        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }
        return null;
    }

}
*/

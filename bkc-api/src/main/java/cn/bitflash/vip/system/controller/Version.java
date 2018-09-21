package cn.bitflash.vip.system.controller;

import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.util.R;
import cn.bitflash.vip.system.feign.SystemFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/system")
@Api(value = "app版本Con",tags={"版本提示"})
public class Version {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemFeign systemFeign;

    @ApiOperation(value="查看app版本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appid", value = "appid",dataType = "String"),
            @ApiImplicitParam(name = "version",value = "app现在版本号",dataType = "String"),
            @ApiImplicitParam(name = "imei",value = "手机imei设备码",dataType = "String")
    })
    @GetMapping("update")
    public R update( @RequestParam String appid, @RequestParam String version, @RequestParam String imei) {
        logger.info(appid + "**" + version + "**" + imei);
        String one = "1";
        String zero = "0";
        String status = "status";
        Map<String, String> map = new HashMap<>();
        AppStatusEntity appStatusEntity = systemFeign.selectAppStatusByAppId(appid);
        if (appStatusEntity == null) {
            map.put(status, "-1");
            return R.ok().put("data", map);
        }
        String[] newVersion = appStatusEntity.getVersion().split("\\.");
        String[] appVersion = version.split("\\.");
        map.put(status, one);
        map.put("url", appStatusEntity.getUrl());
        map.put("note", appStatusEntity.getNote());
        map.put("title", appStatusEntity.getTitle());
        //长度以数据库为准时
        /**
         *  state1: 取每一位与数据库对比，只有当app的版本号小于数据库的版本时，才会提示更新
         */
        if (newVersion.length <= appVersion.length) {
            for (int i = 0; i < newVersion.length; i++) {
                int nv = Integer.parseInt(newVersion[i]);
                int av = Integer.parseInt(appVersion[i]);
                //app版本>= 数据库版本，不提示
                if (nv <= av) {
                    map.put(status, zero);
                } else {
                    //app版本< 数据库版本，提示
                    map.put(status, one);
                    break;
                    //return R.ok().put("data", map);
                }
            }
        }
        //长度以app为准时
        /**
         * state1: 数据库版本9.1.14.1，app版本9.1.14,提示更新
         *         对比下来数据是一样的，所以要在最后一次循环判断下之前对比的结果，如果都一样，则证明数据库版本号长且前几位与app版本一直，则提示更新。
         * state2: 数据库版本8.1.14.1，app版本9.1.14，提示不更新
         */
        else {
            boolean flag = false;
            for (int i = 0; i < appVersion.length; i++) {
                int nv = Integer.parseInt(newVersion[i]);
                int av = Integer.parseInt(appVersion[i]);
                if (nv < av) {
                    flag = true;
                    map.put(status, zero);
                } else if (nv == av) {
                    map.put(status, zero);
                } else {
                    map.put(status, one);
                    break;
                    //return R.ok().put("data", map);
                }
                if (i == appVersion.length - 1 && flag == false) {
                    map.put(status, one);
                    break;
                    //return R.ok().put("data", map);
                }
            }
        }
        return R.ok().put("data", map);

    }

}

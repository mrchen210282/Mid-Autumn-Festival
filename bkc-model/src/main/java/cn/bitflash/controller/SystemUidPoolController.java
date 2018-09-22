package cn.bitflash.controller;


import cn.bitflash.service.SystemUidPoolService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GAOYGUUO
 */
@RestController
public class SystemUidPoolController {

    @Autowired
    private SystemUidPoolService systemUidPoolService;

    /**
     * 取得uid
     *
     * @return
     */
    @PostMapping("/inner/systemUid/selectUid")
    public String selectUid(@RequestParam("mobile") String mobile,@RequestParam("pwd") String pwd) {
        String uid = "";
        if(StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(pwd)) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("mobile",mobile);
            map.put("pwd",pwd);
            uid = systemUidPoolService.selectUid(map);
        }
        return uid;
    }


}

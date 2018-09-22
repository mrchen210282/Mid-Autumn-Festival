package cn.bitflash.controller;


import cn.bitflash.entity.AppStatusEntity;
import cn.bitflash.service.AppStatusService;
import cn.bitflash.service.SystemUidService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYGUUO
 */
@RestController
public class SystemUidController {

    @Autowired
    private SystemUidService systemUidService;

    /**
     * 取得uid
     *
     * @return
     */
    @PostMapping("/inner/systemUid/selectUid")
    public String selectUid() {
        String uid = systemUidService.selectUid();
        return uid;
    }
}

package cn.bitflash.controller;


import cn.bitflash.service.SystemUidPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String selectUid() {
        String uid = systemUidPoolService.selectUid();
        return uid;
    }


}

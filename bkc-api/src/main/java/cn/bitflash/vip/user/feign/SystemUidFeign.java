package cn.bitflash.vip.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "bkc-model")
public interface SystemUidFeign {

    /**
     * system_uid表
     */
    @PostMapping("/inner/systemUid/selectUid")
    String selectUid(@RequestParam("mobile")String mobile, @RequestParam("pwd") String pwd);
}

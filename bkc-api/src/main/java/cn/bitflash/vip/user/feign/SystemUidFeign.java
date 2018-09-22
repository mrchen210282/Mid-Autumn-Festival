package cn.bitflash.vip.user.feign;

import cn.bitflash.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bkc-model")
public interface SystemUidFeign {

    /**
     * system_uid表
     */
    @PostMapping("/inner/systemUid/selectUid")
    String selectUid();
}

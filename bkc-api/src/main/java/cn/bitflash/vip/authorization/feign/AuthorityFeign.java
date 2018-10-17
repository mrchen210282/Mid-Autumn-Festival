package cn.bitflash.vip.authorization.feign;

import cn.bitflash.vip.authorization.entity.UserEmpowerEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bkc-model")
public interface AuthorityFeign {

    /**
     * selectUserEmpowerById
     *
     * @return
     */
    @PostMapping("/inner/userEmpower/selectUserEmpowerById")
    public UserEmpowerEntity selectUserEmpowerById(@RequestParam("clientid") String clientid, @RequestParam("ticket") String ticket);
}

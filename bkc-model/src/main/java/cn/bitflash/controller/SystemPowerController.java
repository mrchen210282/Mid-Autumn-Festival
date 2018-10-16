package cn.bitflash.controller;

import cn.bitflash.entity.SystemPowerEntity;
import cn.bitflash.service.SystemPowerService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemPowerController {

    @Autowired
    private SystemPowerService systemPowerService;

    @PostMapping("/inner/systemPower/selectById")
    public SystemPowerEntity selectById(@RequestParam("id") String id){
        return systemPowerService.selectById(id);
    }

    @PostMapping("/inner/systemPower/selectList")
    public List<SystemPowerEntity> selectList(@RequestParam("minId") int minId,@RequestParam("maxId") int maxId){
        return systemPowerService.selectList(new EntityWrapper<SystemPowerEntity>().between("id",minId,maxId));
    }

    @PostMapping("/inner/systemPower/selectByupgradeNum")
    public  SystemPowerEntity selectByupgradeNum(@RequestParam("upgradeNum") String upgradeNum){
        return systemPowerService.selectOne(new EntityWrapper<SystemPowerEntity>().eq("upgrade_num",upgradeNum));
    }

    @PostMapping("/inner/systemPower/selectSystemPowerByNum")
    public SystemPowerEntity selectSystemPowerByNum(@RequestParam("num")Integer num){
        return systemPowerService.selectOne(new EntityWrapper<SystemPowerEntity>().eq("upgrade_num",num));
    }
}

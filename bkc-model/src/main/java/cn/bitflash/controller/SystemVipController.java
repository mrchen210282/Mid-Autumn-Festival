package cn.bitflash.controller;

import cn.bitflash.entity.SystemVipEntity;
import cn.bitflash.service.SystemVipService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemVipController {

    @Autowired
    private SystemVipService systemVipService;

    @PostMapping("/inner/systemvip/selectById")
    public SystemVipEntity selectByid(@RequestParam("id") String id) {
        return systemVipService.selectById(id);
    }

    @PostMapping("/innner/systemvip/selectAll")
    public List<SystemVipEntity> selectAll() {
        return systemVipService.selectList(new EntityWrapper<SystemVipEntity>().isNotNull("id"));
    }


}

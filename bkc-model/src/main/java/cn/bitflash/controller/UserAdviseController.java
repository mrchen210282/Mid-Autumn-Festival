package cn.bitflash.controller;

import cn.bitflash.bean.UserAdviseBean;
import cn.bitflash.entity.UserAdviseEntity;
import cn.bitflash.service.UserAdviseService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAdviseController {

    @Autowired
    private UserAdviseService userAdviseService;

    @GetMapping("/inner/userAdvise/insert")
    public void insert(@RequestBody UserAdviseEntity userAdviseEntity){
        userAdviseService.insert(userAdviseEntity);
    }

    @GetMapping("/inner/userAdvise/list/{page}")
    public List<UserAdviseBean> adviseList(@PathVariable Integer page){
        return userAdviseService.adviseList(page);
    }

    @GetMapping("/inner/userAdvise/count")
    public Integer adviseCount(){
        System.out.println(userAdviseService.adviseCount());
        return userAdviseService.adviseCount();
    }

    @GetMapping("/inner/userAdvise/check/{id}")
    public UserAdviseEntity adviseInfo(@PathVariable Integer id){
        return userAdviseService.selectOne(new EntityWrapper<UserAdviseEntity>().eq("id",id));
    }

    @GetMapping("inner/userAdvise/delete/{id}")
    public void adviseDelete(@PathVariable Integer id){
        userAdviseService.deleteById(id);
    }
}

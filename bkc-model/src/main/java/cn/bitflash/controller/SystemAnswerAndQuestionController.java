package cn.bitflash.controller;

import cn.bitflash.entity.SystemQuestionEntity;
import cn.bitflash.service.SystemQuestionService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemAnswerAndQuestionController {

    @Autowired
    private SystemQuestionService systemQuestionService;

    @GetMapping("/inner/sysQuestion/selectList")
    public List<SystemQuestionEntity> selectList() {
        return systemQuestionService.selectList(new EntityWrapper<SystemQuestionEntity>().orderBy("id"));
    }

    @GetMapping("/inner/sysAnswer/selectQuestionById")
    public SystemQuestionEntity selectById(@RequestParam String questionId) {
        return systemQuestionService.selectById(questionId);
    }

}

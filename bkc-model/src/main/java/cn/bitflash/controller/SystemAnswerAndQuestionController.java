package cn.bitflash.controller;

import cn.bitflash.entity.SystemAnswerEntity;
import cn.bitflash.entity.SystemQuestionEntity;
import cn.bitflash.service.SystemAnswerService;
import cn.bitflash.service.SystemQuestionService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.util.Date;
import java.util.List;

@RestController
public class SystemAnswerAndQuestionController {

    @Autowired
    private SystemAnswerService systemAnswerService;

    @Autowired
    private SystemQuestionService systemQuestionService;

    @GetMapping("/inner/sysQuestion/selectList")
    public List<SystemQuestionEntity> selectList(){
        return systemQuestionService.selectList(new EntityWrapper<SystemQuestionEntity>().orderBy("id"));
    }

    @GetMapping("/inner/sysAnswer/selectByQuestionId")
    public SystemAnswerEntity selectById(@RequestParam String questionId){
        return systemAnswerService.selectOne(new EntityWrapper<SystemAnswerEntity>().eq("question_id",questionId));
    }

    /**
     * 获取问题列表
     */
    @GetMapping("/inner/sysQueAnws/list/{page}")
    public List<SystemQuestionEntity> questionList(@PathVariable Integer page){
        return systemQuestionService.questionList(page);
    }

    /**
     * 获取问题详情
     */
    @GetMapping("/inner/sysQueAnws/info/{id}")
    public SystemQuestionEntity questionCheck(@PathVariable Integer id){
        return systemQuestionService.selectOne(new EntityWrapper<SystemQuestionEntity>().eq("id",id));
    }

    /**
     * 数量
     */
    @GetMapping("inner/sysQueAnws/count")
    public Integer questionCount(){
        return systemQuestionService.questionCount();
    }

    /**
     * 删除列表
     */
    @GetMapping("/inner/sysQueAnws/delete/{id}")
    public void questionDelete(@PathVariable Integer id){
        systemQuestionService.deleteById(id);
    }

    /**
     * 修改问题和答案
     */
    @PostMapping(value = "/inner/sysQueAnws/update",consumes = "application/json")
    public void updateQueAnws(@RequestBody SystemQuestionEntity entity){
        systemQuestionService.updateById(entity);
    }

    /**
     * 添加
     */
    @PostMapping(value = "/inner/sysQueAnws/add",consumes = "application/json")
    public void questionDelete(@RequestBody SystemQuestionEntity entity){
        entity.setCreateTime(new Date());
        systemQuestionService.insert(entity);
    }
}

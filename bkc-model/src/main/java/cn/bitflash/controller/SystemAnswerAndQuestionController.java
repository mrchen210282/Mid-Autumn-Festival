package cn.bitflash.controller;

import cn.bitflash.entity.SystemQuestionEntity;
import cn.bitflash.service.SystemQuestionService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class SystemAnswerAndQuestionController {

    @Autowired
    private SystemQuestionService systemQuestionService;

    @GetMapping("/inner/sysQuestion/selectList")
    public List<SystemQuestionEntity> selectList(){
        return systemQuestionService.selectList(new EntityWrapper<SystemQuestionEntity>().orderBy("id"));
    }

    /**
     * ��ȡ�����б�
     */
    @GetMapping("/inner/sysQueAnws/list/{page}")
    public List<SystemQuestionEntity> questionList(@PathVariable Integer page){
        return systemQuestionService.questionList(page);
    }

    /**
     * ��ȡ��������
     */
    @GetMapping("/inner/sysQueAnws/info/{id}")
    public SystemQuestionEntity questionCheck(@PathVariable Integer id){
        return systemQuestionService.selectOne(new EntityWrapper<SystemQuestionEntity>().eq("id",id));
    }

    /**
     * ����
     */
    @GetMapping("inner/sysQueAnws/count")
    public Integer questionCount(){
        return systemQuestionService.questionCount();
    }

    /**
     * ɾ���б�
     */
    @GetMapping("/inner/sysQueAnws/delete/{id}")
    public void questionDelete(@PathVariable Integer id){
        systemQuestionService.deleteById(id);
    }

    /**
     * �޸�����ʹ�
     */
    @PostMapping(value = "/inner/sysQueAnws/update",consumes = "application/json")
    public void updateQueAnws(@RequestBody SystemQuestionEntity entity){
        systemQuestionService.updateById(entity);
    }

    /**
     * ���
     */
    @PostMapping(value = "/inner/sysQueAnws/add",consumes = "application/json")
    public void questionDelete(@RequestBody SystemQuestionEntity entity){
        entity.setCreateTime(new Date());
        systemQuestionService.insert(entity);
    }
}

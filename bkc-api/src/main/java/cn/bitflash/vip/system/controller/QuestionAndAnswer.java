package cn.bitflash.vip.system.controller;

import cn.bitflash.entity.SystemAnswerEntity;
import cn.bitflash.entity.SystemQuestionEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.system.feign.SystemFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionAndAnswer {

    @Autowired
    private SystemFeign systemFeign;

    @GetMapping("getQuestions")
    public R getQuestions() {
        List<SystemQuestionEntity> systemQuestionEntities = systemFeign.selectList();
        return R.ok().put("questions", systemQuestionEntities);
    }

    @GetMapping("getAnswer")
    public R getAnswer(@RequestParam("questionId") String questionId) {
        SystemAnswerEntity answerEntity = systemFeign.selectById(questionId);
        return R.ok().put("answer", answerEntity.getAnswer());
    }
}

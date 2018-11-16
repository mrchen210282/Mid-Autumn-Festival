package cn.bitflash.controller;

import cn.bitflash.bean.ImgForm;
import cn.bitflash.entity.SystemAnswerEntity;
import cn.bitflash.entity.SystemQuestionEntity;
import cn.bitflash.service.SystemAnswerService;
import cn.bitflash.service.SystemQuestionService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

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

}

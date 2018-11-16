package cn.bitflash.service;

import cn.bitflash.entity.SystemQuestionEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface SystemQuestionService extends IService<SystemQuestionEntity> {
    List<SystemQuestionEntity> questionList(Integer page);
    Integer questionCount();
}

package cn.bitflash.service;

import cn.bitflash.entity.DailyTotalNpcEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.Date;
import java.util.List;

public interface DailyTotalNpcService extends IService<DailyTotalNpcEntity> {

    List<DailyTotalNpcEntity> npcList(String today);
}

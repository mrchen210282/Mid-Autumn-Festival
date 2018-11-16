package cn.bitflash.dao;

import cn.bitflash.entity.DailyTotalNpcEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface DailyTotalNpcDao extends BaseMapper<DailyTotalNpcEntity> {
    List<DailyTotalNpcEntity> npcList(String today);
}

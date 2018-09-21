package cn.bitflash.dao;


import cn.bitflash.entity.PlatformConfigEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface PlatFormConfigDao extends BaseMapper<PlatformConfigEntity> {

    String getValue(@Param("key") String key);
}

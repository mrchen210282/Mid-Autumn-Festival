package cn.bitflash.dao;

import cn.bitflash.entities.UserRelationEntity;
import cn.bitflash.entity.UserRelationJoinAccountEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户体系
 *
 * @author soso
 * @date 2018年6月1日 下午3:32:51
 */
public interface UserRelationDao extends BaseMapper<UserRelationEntity> {

    void insertTreeNode(@Param("f_uid") String f_uid, @Param("c_uid") String c_uid, @Param("invitation") String invitation);

    List<UserRelationJoinAccountEntity> selectTreeNodes(String f_uid);
}

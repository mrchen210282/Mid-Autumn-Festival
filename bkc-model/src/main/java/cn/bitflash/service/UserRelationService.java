package cn.bitflash.service;

import cn.bitflash.entity.UserRelationEntity;
import cn.bitflash.entity.UserRelationJoinAccountEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author soso
 * @date 2018年5月21日 下午4:47:36
 */

public interface UserRelationService extends IService<UserRelationEntity> {

    void insertTreeNode(String f_uid, String c_uid, String invitation_code);

    List<UserRelationJoinAccountEntity> selectTreeNodes(String f_uid);



}

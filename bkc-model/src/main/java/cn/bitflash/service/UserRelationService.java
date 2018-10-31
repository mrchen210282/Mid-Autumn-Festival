package cn.bitflash.service;


import cn.bitflash.bean.AdminRelationBean;
import cn.bitflash.bean.UserInfoBean;
import cn.bitflash.bean.UserRelationJoinNpcAndHlbean;
import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.entity.UserRelationEntity;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface UserRelationService extends IService<UserRelationEntity> {

    List<UserRelationEntity> selectTreeNodes(String uid);

    Boolean insertTreeNode(String pid,String uid, String code);

    List<UserRelationJoinNpcAndHlbean> selectTreeNood(String f_uid);

    List<UserInfoBean> selectRelationAndMobileByCode(String code);

    List<UserInvitationCodeEntity> selectUserInvitationCode(String str);

    List<UserRelationEntity> selectUserRelationCode(@Param("code") String code);

    List<AdminRelationBean> findTree();

    AdminRelationBean findNode(String realname);


}

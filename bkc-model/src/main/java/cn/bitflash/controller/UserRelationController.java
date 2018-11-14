package cn.bitflash.controller;


import cn.bitflash.bean.AdminRelationBean;
import cn.bitflash.bean.UserInfoBean;
import cn.bitflash.bean.UserRelationJoinNpcAndHlbean;
import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.entity.UserRelationEntity;
import cn.bitflash.service.UserInvitationCodeService;
import cn.bitflash.service.UserRelationService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GAOYGUUO
 */
@RestController
public class UserRelationController {

    @Autowired
    private UserRelationService userRelationService;

    @Autowired
    private UserInvitationCodeService userInvitationCodeService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userRelation/selectById")
    public UserRelationEntity selectById(@RequestParam("id") String id) {
        UserRelationEntity entity = userRelationService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userRelation/updateById")
    public boolean updateById(@RequestBody JSONObject json) throws Exception {
        UserRelationEntity entity = (UserRelationEntity) JSONObject.parseObject(json.toString(), UserRelationEntity.class);
        return userRelationService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userRelation/insert")
    public boolean insert(@RequestBody JSONObject json) throws Exception {
        UserRelationEntity entity = (UserRelationEntity) JSONObject.parseObject(json.toString(), UserRelationEntity.class);
        return userRelationService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userRelation/deleteById")
    public boolean deleteById(@RequestParam("id") String id) throws Exception {
        return userRelationService.deleteById(id);
    }

    @PostMapping("/inner/userRelation/selectTreeNodes")
    public List<UserRelationEntity> selectTreeNodes(@RequestParam("uid") String uid){
        return userRelationService.selectTreeNodes(uid);
    }

    @PostMapping("/inner/userRelation/insertTreeNode")
    public Boolean insertTreeNode(@RequestParam("pid") String pid, @RequestParam("uid") String uid,
                           @RequestParam("code") String code){
        return userRelationService.insertTreeNode(pid,uid,code);

    }

    @PostMapping("/inner/userRelation/selectTreeNood")
    public List<UserRelationJoinNpcAndHlbean> selectTreeNood(@RequestParam("f_uid")String f_uid){
        return userRelationService.selectTreeNood(f_uid);
    }

    @PostMapping("/inner/userRelation/selectRelationByCode")
    public List<UserRelationEntity> selectRelationByCode(@RequestParam("code") String code){
        return userRelationService.selectList(new EntityWrapper<UserRelationEntity>().eq("father_code",code));
    }

    @PostMapping("/inner/userRelation/selectRelationAndMobileByCode")
    public List<UserInfoBean> selectRelationAndMobileByCode(@RequestParam("code") String code){
        return userRelationService.selectRelationAndMobileByCode(code);
    }

    @PostMapping("/inner/userRelation/selectUserInvitationCode")
    public List<UserInvitationCodeEntity> selectUserInvitationCode(@RequestParam("code") String code){
        return userRelationService.selectUserInvitationCode(code);
    }

    @PostMapping("/inner/userRelation/selectUserRelation")
    public UserRelationEntity selectUserRelation(@RequestParam("uid") String uid) {
        UserRelationEntity userRelationEntity = userRelationService.selectOne(new EntityWrapper<UserRelationEntity>().eq("old_uid",uid));
        return userRelationEntity;
    }

    @PostMapping("/inner/userRelation/selectUserRelationCode")
    public List<UserRelationEntity> selectUserRelationCode(@RequestParam("code") String code) {
        List<UserRelationEntity> list = userRelationService.selectUserRelationCode(code);
        return list;
    }

    /**
     * admin
     * findTree
     */
    @GetMapping("/inner/userRelation/findTree")
    public List<AdminRelationBean> findTree() {
        return userRelationService.findTree();
    }

    /**
     * findNode
     */
    @GetMapping("/inner/userRelation/findTree/{realname}")
    public AdminRelationBean findNode(@PathVariable String realname) {
        return userRelationService.findNode(realname);
    }

    /**
     * findById
     */
    @GetMapping("/inner/userRelation/findById/{left}")
    public UserRelationEntity findById(@PathVariable String left) {
        return userRelationService.selectOne(new EntityWrapper<UserRelationEntity>().eq("lft",Integer.parseInt(left)));
    }

    /**
     * findCode
     */
    @GetMapping("/inner/userRelation/findCode/{left}")
    public List<AdminRelationBean> findCode(@PathVariable Integer left) {
        UserRelationEntity relation = userRelationService.selectOne(new EntityWrapper<UserRelationEntity>().eq("lft",left));
        UserInvitationCodeEntity userCode = userInvitationCodeService.selectOne(new EntityWrapper<UserInvitationCodeEntity>().eq("uid",relation.getUid()));
        return userRelationService.findCode(userCode.getCode(),relation.getUid());
    }
}

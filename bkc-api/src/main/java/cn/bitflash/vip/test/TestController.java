package cn.bitflash.vip.test;

import cn.bitflash.entity.UserInfoEntity;
import cn.bitflash.entity.UserInvitationCodeEntity;
import cn.bitflash.entity.UserRelationEntity;
import cn.bitflash.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private TestFeign feign;

    /**
     * 获取当天起，前7天的价格
     *
     * @return
     */
    @PostMapping("demo")
    public R getWeekPriceRate() {

        List<UserInvitationCodeEntity> list = feign.selectUserInvitationCode("");
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {

                UserInvitationCodeEntity userInvitationCodeEntity = list.get(i);
                String lftcode = userInvitationCodeEntity.getLftCode();
                List<UserRelationEntity> listRelation = feign.selectUserRelationCode(lftcode);
                if (null != listRelation && listRelation.size() > 0) {
                    for (int j = 0; j < listRelation.size(); j++) {
                        UserRelationEntity userRelationEntity = listRelation.get(j);

                        UserInfoEntity userInfoEntity = feign.selectById(userRelationEntity.getUid());
                        if(null != userInfoEntity) {
                            userInfoEntity.setInvitationCode(lftcode);
                            userInfoEntity.setArea("L");
                            feign.updateById(userInfoEntity);
                        }
                    }

                }

                List<UserRelationEntity> listRelationRgt = feign.selectUserRelationCode(userInvitationCodeEntity.getRgtCode());
                if (null != listRelationRgt && listRelationRgt.size() > 0) {
                    for (int j = 0; j < listRelationRgt.size(); j++) {
                        UserRelationEntity userRelationEntity = listRelationRgt.get(j);
                        UserInfoEntity userInfoEntity = feign.selectById(userRelationEntity.getUid());
                        if(null != userInfoEntity) {
                            userInfoEntity.setInvitationCode(lftcode);
                            userInfoEntity.setArea("R");
                            feign.updateById(userInfoEntity);

                        }
                        userRelationEntity.setFatherCode(lftcode);
                        feign.updateUserRelationById(userRelationEntity);
                    }

                }

                //询user_infoid
                UserInfoEntity userInfoEntity = feign.selectUserInfoByOldUid(userInvitationCodeEntity.getUid());

                UserInvitationCodeEntity userInvitationCodeEntity1 = new UserInvitationCodeEntity();
                userInvitationCodeEntity1.setUid(userInfoEntity.getUid());
                userInvitationCodeEntity1.setCode(lftcode);
                feign.insert(userInvitationCodeEntity1);


            }
        }


        return R.ok();
        //return R.ok().put("date", date).put("price", price).put("yesterday", yestPrice);
    }
}

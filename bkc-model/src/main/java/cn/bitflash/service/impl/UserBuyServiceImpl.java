package cn.bitflash.service.impl;

import cn.bitflash.entity.UserBuyBean;
import cn.bitflash.entity.UserBuyEntity;
import cn.bitflash.entity.UserBuyMessageBean;
import cn.bitflash.dao.UserBuyDao;
import cn.bitflash.service.UserBuyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 求购
 *
 * @author gaoyuguo
 * @date 2018-8-28 15:22:06
 */
@Service("userBuyService")
public class UserBuyServiceImpl extends ServiceImpl<UserBuyDao, UserBuyEntity> implements UserBuyService {

    private final Logger logger = LoggerFactory.getLogger(UserBuyServiceImpl.class);

    @Override
    public List<UserBuyMessageBean> getBuyMessage(String uid, Integer pages) {
        return baseMapper.getBuyMessage(uid, pages);
    }

    @Override
    public Integer getNumToPaging() {
        return baseMapper.getNumToPaging();
    }

    @Override
    public List<UserBuyBean> selectBuyList(String uid, Integer pages) {
        return baseMapper.selectBuyList(uid, pages);
    }

    @Override
    public List<UserBuyBean> selectAppealList(String uid, Integer pages) {
        return baseMapper.selectAppealList(uid, pages);
    }

    @Override
    public Integer selectAppealCount(String uid) {
        return baseMapper.selectAppealCount(uid);
    }

    @Override
    public Integer selectUserBuyOwnCount(String uid) {
        return baseMapper.selectUserBuyOwnCount(uid);
    }
}

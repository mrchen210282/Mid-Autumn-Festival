package cn.bitflash.controller;

import cn.bitflash.entity.UserDigitalIncomeEntity;
import cn.bitflash.exception.RRException;
import cn.bitflash.service.UserDigitalIncomeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOYUGUO
 */
@RestController
public class UserDigitalIncomeController {

    @Autowired
    private UserDigitalIncomeService userDigitalIncomeService;

    /**
     * selectById
     *
     * @return
     */
    @PostMapping("/inner/userDigitalIncome/selectById")
    public UserDigitalIncomeEntity selectById(@RequestParam("id") String id) {
        UserDigitalIncomeEntity entity = userDigitalIncomeService.selectById(id);
        return entity;
    }

    /**
     * updateById
     *
     * @return
     */
    @PostMapping("/inner/userDigitalIncome/updateById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void updateById(@RequestBody JSONObject json) {
        UserDigitalIncomeEntity entity = new UserDigitalIncomeEntity();
        entity.setUid(json.getString("uid"));
        entity.setPurchase(json.getBigDecimal("purchase"));
        entity.setFrozenAssets(json.getBigDecimal("frozenAssets"));
        entity.setPurchaseRelease(json.getBigDecimal("purchaseRelease"));
        entity.setDailyRelease(json.getBigDecimal("dailyRelease"));
        entity.setTotelRelease(json.getBigDecimal("totelRelease"));
        entity.setAvailableAssets(json.getBigDecimal("availableAssets"));
        entity.setLftAchievement(json.getBigDecimal("lftAchievement"));
        entity.setRgtAchievement(json.getBigDecimal("rgtAchievement"));
        entity.setCreateTime(json.getDate("createTime"));
        userDigitalIncomeService.updateById(entity);
    }

    /**
     * insert
     *
     * @return
     */
    @PostMapping("/inner/userDigitalIncome/insert")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void insert(@RequestBody JSONObject json) {
        UserDigitalIncomeEntity entity = new UserDigitalIncomeEntity();
        entity.setUid(json.getString("uid"));
        entity.setPurchase(json.getBigDecimal("purchase"));
        entity.setFrozenAssets(json.getBigDecimal("frozenAssets"));
        entity.setPurchaseRelease(json.getBigDecimal("purchaseRelease"));
        entity.setDailyRelease(json.getBigDecimal("dailyRelease"));
        entity.setTotelRelease(json.getBigDecimal("totelRelease"));
        entity.setAvailableAssets(json.getBigDecimal("availableAssets"));
        entity.setLftAchievement(json.getBigDecimal("lftAchievement"));
        entity.setRgtAchievement(json.getBigDecimal("rgtAchievement"));
        entity.setCreateTime(json.getDate("createTime"));
        userDigitalIncomeService.insert(entity);
    }

    /**
     * deleteById
     *
     * @return
     */
    @PostMapping("/inner/userDigitalIncome/deleteById")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RRException.class)
    public void deleteById(@RequestParam("id") String id) {
        userDigitalIncomeService.deleteById(id);
    }
}

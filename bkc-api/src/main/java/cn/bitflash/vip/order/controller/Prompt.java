package cn.bitflash.vip.order.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.UserMarketTradeEntity;
import cn.bitflash.utils.R;
import cn.bitflash.vip.buy.Bean.UserBuyBean;
import cn.bitflash.vip.order.entity.UserComplaintBean;
import cn.bitflash.vip.order.feign.OrderFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("prompt")
@Api("提示")
public class Prompt {

    @Autowired
    private OrderFeign orderFeign;

    @Login
    @PostMapping("/show")
    @ApiOperation("显示红点")
    public R selectAppealList(@RequestAttribute("uid") String uid) {
        List<Integer> list = new ArrayList<Integer>();
//        //查看买入
////        int countTrade = orderFeign.selectTradePrompt(uid);
////        if(countTrade != 0){
////            list.add(1);
////        }else{
////            list.add(0);
////        }
////        //查看卖出
////        int countBuy = orderFeign.selectBuyPrompt(uid);
////        if(countBuy != 0){
////            list.add(1);
////        }else{
////            list.add(0);
////        }
////        //查看申诉
////        int countAppeal = orderFeign.selectAppealPrompt(uid);
////        if(countAppeal != 0){
////            list.add(1);
////        }else{
////            list.add(0);
////        }
////        //查看完成
////        int countSuccess = orderFeign.selectSuccessPrompt(uid);
////        if(countSuccess != 0){
////            list.add(1);
////        }else{
////            list.add(0);
////        }
        return R.ok().put("list",list);
    }

    @Login
    @PostMapping("/del")
    @ApiOperation("申诉详情")
    public R checkAppeal(@ApiParam @RequestParam("id") String id, @RequestAttribute("uid") String uid, @RequestParam("state") String state) {
        return R.ok();
    }
}



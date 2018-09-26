package cn.bitflash.service;

import cn.bitflash.entity.PriceLinechartEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface PriceLinechartService extends IService<PriceLinechartEntity> {

    public PriceLinechartEntity selectPriceLinechart();
}

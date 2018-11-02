package cn.bitflash.service;

import cn.bitflash.entity.PriceLinechartEntity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public interface PriceLinechartService extends IService<PriceLinechartEntity> {

    public PriceLinechartEntity selectPriceLinechart();

    public PriceLinechartEntity selectPriceUs();

    public PriceLinechartEntity selectPriceCny();

    List<PriceLinechartEntity> apilineChart(String startd , String yester);
}

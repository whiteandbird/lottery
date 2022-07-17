package com.itwang.lottery.domain.award.service.factory;

import com.itwang.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  10:53
 */
@Service
public class DistributuionGoodsFactory extends GoodsConfig{
    public IDistributionGoods getDistributionGoodsService(Integer awardType){
        return goodsDistributionMap.get(awardType);
    }
}

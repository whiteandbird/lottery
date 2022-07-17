package com.itwang.lottery.domain.award.service.factory;

import com.itwang.lottery.domain.award.service.goods.IDistributionGoods;
import com.itwang.lottery.domain.award.service.goods.impl.CouponGoods;
import com.itwang.lottery.domain.award.service.goods.impl.DescGoods;
import com.itwang.lottery.domain.award.service.goods.impl.PhysicalGoods;
import com.itwang.lottery.domain.award.service.goods.impl.RedeemCodeGoods;
import it.comwang.lottery.common.Constants;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  10:54
 */
public class GoodsConfig {

    protected static Map<Integer, IDistributionGoods> goodsDistributionMap = new HashMap<>();

    @Resource
    private DescGoods descGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @PostConstruct
    public void init(){
        goodsDistributionMap.put(Constants.AwardType.CouponGoods.getCode(), couponGoods);
        goodsDistributionMap.put(Constants.AwardType.RedeemCodeGoods.getCode(), redeemCodeGoods);
        goodsDistributionMap.put(Constants.AwardType.DESC.getCode(), descGoods);
        goodsDistributionMap.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
    }
}

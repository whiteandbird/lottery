package com.itwang.lottery.domain.award.service.goods.impl;

import com.itwang.lottery.domain.award.model.req.GoodReq;
import com.itwang.lottery.domain.award.model.res.DistributionRes;
import com.itwang.lottery.domain.award.service.goods.DistributionBase;
import com.itwang.lottery.domain.award.service.goods.IDistributionGoods;
import it.comwang.lottery.common.Constants;
import org.springframework.stereotype.Component;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  10:07
 */
@Component
public class DescGoods extends DistributionBase implements IDistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodReq goodReq) {
        logger.info("模拟调用描述发奖 uid:{} awardContent:{}", goodReq.getuId(), goodReq.getAwardContent());
        super.updateUserAwardState(goodReq.getuId(), goodReq.getOrderId(), goodReq.getAwardId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
        return new DistributionRes(goodReq.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.DESC.getCode();
    }
}

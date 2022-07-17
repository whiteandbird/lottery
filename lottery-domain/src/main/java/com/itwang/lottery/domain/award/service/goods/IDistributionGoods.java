package com.itwang.lottery.domain.award.service.goods;

import com.itwang.lottery.domain.award.model.req.GoodReq;
import com.itwang.lottery.domain.award.model.res.DistributionRes;

public interface IDistributionGoods {

    DistributionRes doDistribution(GoodReq goodReq);

    Integer getDistributionGoodsName();
}

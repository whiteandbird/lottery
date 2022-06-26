package com.itwang.lottery.domain.service.algorithm.impl;

import com.itwang.lottery.domain.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  14:47
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        int randomIdx  = new SecureRandom().nextInt(100)+1;
        int idx = super.hashIdx(randomIdx);
        String awardId = rateTuple[idx];
        if(excludeAwardIds.contains(awardId)) return "原因: 排除 未中奖";

        return awardId;
    }
}

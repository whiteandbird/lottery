package com.itwang.lottery.domain.strategy.service.algorithm.impl;

import cn.hutool.core.collection.CollUtil;
import com.itwang.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.itwang.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  11:40
 */

@Component("defaultRateRandomDrawAlgorithm")
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        BigDecimal differenceDenominator = BigDecimal.ZERO;
        List<AwardRateInfo> differenceAwardRateList = new ArrayList<>();
        List<AwardRateInfo> awardRateIntervalList = awardRateInfoMap.get(strategyId);

        for(AwardRateInfo awardRateInfo : awardRateIntervalList){
            String awardId = awardRateInfo.getAwardId();
            if(excludeAwardIds.contains(awardId)){
                continue;
            }
            differenceAwardRateList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }
        if(CollUtil.isEmpty(differenceAwardRateList)) return null;
        if(CollUtil.size(awardRateIntervalList) == 1) return differenceAwardRateList.get(0).getAwardId();

        SecureRandom secureRandom = new SecureRandom();
        int randomVal = secureRandom.nextInt(100) + 1;
        String awardId = null;
        int cursorVal = 0;
        for(AwardRateInfo awardRateInfo : differenceAwardRateList){
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDenominator, 2 , BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if(randomVal <= (rateVal+cursorVal)){
                awardId = awardRateInfo.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }

        return awardId;
    }
}

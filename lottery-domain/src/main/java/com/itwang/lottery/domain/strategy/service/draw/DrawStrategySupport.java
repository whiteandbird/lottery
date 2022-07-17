package com.itwang.lottery.domain.strategy.service.draw;

import com.itwang.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.itwang.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.itwang.lottery.domain.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:28  21:34
 */
public class DrawStrategySupport extends DrawConfig{

    @Resource
    protected IStrategyRepository strategyRepository;

    protected StrategyRich queryStrategyRich(Long strategyId){
        return  strategyRepository.queryStrategyRich(strategyId);
    }

    protected AwardBriefVO queryAwardInfoByAwardId(String awardId){
        return strategyRepository.queryAwardInfo(awardId);
    }
}

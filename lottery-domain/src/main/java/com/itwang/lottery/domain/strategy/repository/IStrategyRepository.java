package com.itwang.lottery.domain.strategy.repository;

import com.itwang.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.itwang.lottery.domain.strategy.model.vo.AwardBriefVO;

import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  9:53
 */
public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    AwardBriefVO queryAwardInfo(String awardId);

    List<String> queryNostockStrategyAwardList(Long strategyId);

    boolean deductStock(Long strategyId, String awardId);
}

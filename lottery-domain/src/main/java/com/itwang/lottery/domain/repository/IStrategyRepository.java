package com.itwang.lottery.domain.repository;

import com.itwang.lottery.domain.model.aggregates.StrategyRich;
import com.itwang.lottery.infrastructure.po.Award;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  9:53
 */
public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);
}

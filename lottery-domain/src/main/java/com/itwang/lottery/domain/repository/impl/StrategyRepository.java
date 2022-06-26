package com.itwang.lottery.domain.repository.impl;

import com.itwang.lottery.domain.model.aggregates.StrategyRich;
import com.itwang.lottery.domain.repository.IStrategyRepository;
import com.itwang.lottery.infrastructure.dao.IAwardDao;
import com.itwang.lottery.infrastructure.dao.IStrategyDao;
import com.itwang.lottery.infrastructure.dao.IStrategyDetailDao;
import com.itwang.lottery.infrastructure.po.Award;
import com.itwang.lottery.infrastructure.po.Strategy;
import com.itwang.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  10:39
 */
@Component
public class StrategyRepository  implements IStrategyRepository {
    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy =  strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList =strategyDetailDao.queryStrategyDetail(strategyId);
        return new StrategyRich(strategyId, strategy, strategyDetailList);
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }
}

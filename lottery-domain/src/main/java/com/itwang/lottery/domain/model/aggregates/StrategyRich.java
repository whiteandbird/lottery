package com.itwang.lottery.domain.model.aggregates;

import com.itwang.lottery.infrastructure.po.Strategy;
import com.itwang.lottery.infrastructure.po.StrategyDetail;

import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  9:54
 */
public class StrategyRich {

    private Long strategyId;

    private Strategy strategy;

    private List<StrategyDetail> strategyDetailList;

    public StrategyRich(){

    }

    public StrategyRich(Long strategyId, Strategy strategy, List<StrategyDetail> strategyDetailList){
        this.strategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetailList = strategyDetailList;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<StrategyDetail> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetail> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }
}

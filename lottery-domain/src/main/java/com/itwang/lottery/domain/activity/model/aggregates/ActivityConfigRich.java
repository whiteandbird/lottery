package com.itwang.lottery.domain.activity.model.aggregates;

import com.itwang.lottery.domain.activity.model.vo.ActivityVO;
import com.itwang.lottery.domain.activity.model.vo.AwardVO;
import com.itwang.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.itwang.lottery.domain.activity.model.vo.StrategyVO;

import java.util.List;


/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  13:55
 */
public class ActivityConfigRich {

    private ActivityVO activity;

    private StrategyVO strategy;

    private List<AwardVO> awardList;

//    private List<StrategyDetailVO> strategyDetailList;

    public ActivityConfigRich(ActivityVO activity, StrategyVO strategy, List<AwardVO> awardList) {
        this.activity = activity;
        this.strategy = strategy;
        this.awardList = awardList;
    }

    public ActivityVO getActivity() {
        return activity;
    }

    public void setActivity(ActivityVO activity) {
        this.activity = activity;
    }

    public StrategyVO getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyVO strategy) {
        this.strategy = strategy;
    }

    public List<AwardVO> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<AwardVO> awardList) {
        this.awardList = awardList;
    }

//    public List<StrategyDetailVO> getStrategyDetailList() {
//        return strategyDetailList;
//    }
//
//    public void setStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
//        this.strategyDetailList = strategyDetailList;
//    }
}

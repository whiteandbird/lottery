package com.itwang.lottery.domain.activity.repository;

import com.itwang.lottery.domain.activity.model.vo.ActivityVO;
import com.itwang.lottery.domain.activity.model.vo.AwardVO;
import com.itwang.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.itwang.lottery.domain.activity.model.vo.StrategyVO;
import it.comwang.lottery.common.Constants;

import java.util.List;

public interface IActivityRepository {

    void addActivity(ActivityVO activity);

    void addAward(List<AwardVO> awardList);

    void addStrategy(StrategyVO strategy);

    void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList);

    boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState);
}

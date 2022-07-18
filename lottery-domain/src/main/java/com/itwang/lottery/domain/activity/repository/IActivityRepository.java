package com.itwang.lottery.domain.activity.repository;

import com.itwang.lottery.domain.activity.model.req.PartakeReq;
import com.itwang.lottery.domain.activity.model.vo.*;
import it.comwang.lottery.common.Constants;

import java.util.List;

public interface IActivityRepository {

    void addActivity(ActivityVO activity);

    void addAward(List<AwardVO> awardList);

    void addStrategy(StrategyVO strategy);

    void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList);

    boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState);


    /**
     * 查询活动账单信息
     * @param req
     * @return
     */
    ActivityBillVO queryActivityBill(PartakeReq req);
    /**
     * 扣减活动库存 活动应该限制了人数 此处表示扣减参与人数
     * @param activity
     * @return
     */
    int subtractionActivityStock(Long activity);
}

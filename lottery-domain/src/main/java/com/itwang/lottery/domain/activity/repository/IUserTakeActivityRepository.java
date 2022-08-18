package com.itwang.lottery.domain.activity.repository;


import com.itwang.lottery.domain.activity.model.vo.UserTakeActivityVO;

import java.util.Date;

/**
 * 用户参与活动仓储接口
 */
public interface IUserTakeActivityRepository {

    /**
     * 扣减个人参与次数
     * @return
     */
    int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date userTakeTime);

    /**
     * 参与活动
     * @param takeId 领取id
     */
    void takeActivity(Long activityId, String activityName,Long strategyId, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeTime, Long takeId);

    UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);
}

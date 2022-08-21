package com.itwang.lottery.domain.activity.repository;


import com.itwang.lottery.domain.activity.model.vo.DrawOrderVO;
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
     * 锁定活动领取记录
     * @param uId
     * @param activityId
     * @param takeId
     * @return
     */
    int lockTakeActivity(String uId, Long activityId, Long takeId);

    /**
     * 参与活动
     * @param takeId 领取id
     */
    void takeActivity(Long activityId, String activityName,Long strategyId, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeTime, Long takeId);

    /**
     * 保存抽奖信息
     * @param drawOrder
     */
    void saveUserStrategyExport(DrawOrderVO drawOrder);

    /**
     * 查询是否存在未抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     * @param activityId
     * @param uId
     * @return
     */
    UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);
}

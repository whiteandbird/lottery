package com.itwang.lottery.infrastructure.repository;

import com.itwang.lottery.domain.activity.model.vo.DrawOrderVO;
import com.itwang.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.itwang.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.itwang.lottery.infrastructure.dao.IUserStrategyExportDao;
import com.itwang.lottery.infrastructure.dao.IUserTakeActivityCountDao;
import com.itwang.lottery.infrastructure.dao.IUserTakeActivityDao;
import com.itwang.lottery.infrastructure.po.UserStrategyExport;
import com.itwang.lottery.infrastructure.po.UserTakeActivity;
import com.itwang.lottery.infrastructure.po.UserTakeActivityCount;
import it.comwang.lottery.common.Constants;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:16  22:44
 */
@Repository
public class UserTakeActivityRepository implements IUserTakeActivityRepository {
    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Override
    public int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date userTakeTime) {
        if(null == userTakeLeftCount){
            // 证明用户是第一次参加活动 那么 得初始化数据
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            userTakeActivityCount.setTotalCount(takeCount);
            // 这儿应该是 userTakeLeftCount - 1 直接扣除一次库存
            userTakeActivityCount.setLeftCount(userTakeLeftCount-1);
            userTakeActivityCountDao.insert(userTakeActivityCount);
            return 1;
        }else{
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            return userTakeActivityCountDao.updateLeftCount(userTakeActivityCount);
        }
    }

    @Override
    public int lockTakeActivity(String uId, Long activityId, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setTakeId(takeId);
        return userTakeActivityDao.lockTakeActivity(userTakeActivity);
    }

    @Override
    public void takeActivity(Long activityId, String activityName,Long strategyId, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeTime, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setActivityName(activityName);
        userTakeActivity.setTakeDate(takeTime);
        // 设置领取次数
        if(null == userTakeLeftCount){
            userTakeActivity.setTakeCount(1);
        }else{
            userTakeActivity.setTakeCount(takeCount-userTakeLeftCount);
        }
        String uuId = uId + "_" + activityId + "_" + userTakeActivity.getTakeCount();
        userTakeActivity.setStrategyId(strategyId);
        userTakeActivity.setState(Constants.TaskState.NO_USED.getCode());
        userTakeActivity.setUuid(uuId);
        userTakeActivityDao.insert(userTakeActivity);
    }

    @Override
    public void saveUserStrategyExport(DrawOrderVO drawOrder) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId(drawOrder.getuId());
        userStrategyExport.setActivityId(drawOrder.getActivityId());
        userStrategyExport.setOrderId(drawOrder.getOrderId());
        userStrategyExport.setStrategyId(drawOrder.getStrategyId());
        userStrategyExport.setStrategyMode(drawOrder.getStrategyMode());
        userStrategyExport.setGrantType(drawOrder.getGrantType());
        userStrategyExport.setGrantDate(drawOrder.getGrantDate());
        userStrategyExport.setGrantState(drawOrder.getGrantState());
        userStrategyExport.setAwardId(drawOrder.getAwardId());
        userStrategyExport.setAwardType(drawOrder.getAwardType());
        userStrategyExport.setAwardContent(drawOrder.getAwardContent());
        userStrategyExport.setAwardName(drawOrder.getAwardName());
        userStrategyExport.setUuid(String.valueOf(drawOrder.getOrderId()));

        userStrategyExportDao.insert(userStrategyExport);
    }

    @Override
    public UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setActivityId(activityId);
        // 存在未完成的策略
        UserTakeActivity result = userTakeActivityDao.queryNoConsumedTakeActivityOrder(userTakeActivity);
        if(null == result){
            return null;
        }
        UserTakeActivityVO userTakeActivityVO = new UserTakeActivityVO();
        userTakeActivityVO.setActivityId(result.getActivityId());
        userTakeActivityVO.setStrategyId(result.getStrategyId());
        userTakeActivityVO.setState(result.getState());
        userTakeActivityVO.setTakeId(result.getTakeId());
        return userTakeActivityVO;
    }
}

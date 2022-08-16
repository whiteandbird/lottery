package com.itwang.lottery.infrastructure.repository;

import com.itwang.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.itwang.lottery.infrastructure.dao.IUserTakeActivityCountDao;
import com.itwang.lottery.infrastructure.dao.IUserTakeActivityDao;
import com.itwang.lottery.infrastructure.po.UserTakeActivity;
import com.itwang.lottery.infrastructure.po.UserTakeActivityCount;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:16  22:44
 */
@Component
public class UserTakeActivityRepository implements IUserTakeActivityRepository {
    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;

    @Override
    public int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date userTakeTime) {
        if(null == userTakeLeftCount){
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            userTakeActivityCount.setTotalCount(takeCount);
            userTakeActivityCount.setLeftCount(userTakeLeftCount);
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
    public void takeActivity(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeTime, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setActivityName(activityName);
        userTakeActivity.setTakeDate(takeTime);
        if(null == userTakeLeftCount){
            userTakeActivity.setTakeCount(1);
        }else{
            userTakeActivity.setTakeCount(takeCount-userTakeLeftCount);
        }
        String uuId = uId + "_" + activityId + "_" + userTakeActivity.getTakeCount();
        userTakeActivityDao.insert(userTakeActivity);
    }
}

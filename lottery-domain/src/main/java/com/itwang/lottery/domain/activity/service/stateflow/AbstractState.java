package com.itwang.lottery.domain.activity.service.stateflow;

import com.itwang.lottery.domain.activity.repository.IActivityRepository;
import it.comwang.lottery.common.Constants;
import it.comwang.lottery.common.Result;

import javax.annotation.Resource;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  16:51
 */
public abstract class AbstractState {

    @Resource
    protected IActivityRepository activityRepository;

    public abstract Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState);

    public abstract Result checkPass(Long activity, Enum<Constants.ActivityState> currentStatus);

    public abstract Result checkRefuse(Long activity, Enum<Constants.ActivityState> currentStatus);

    public abstract Result checkRevoke(Long activity, Enum<Constants.ActivityState> currentStatus);

    public abstract Result close(Long activity, Enum<Constants.ActivityState> currentStatus);

    public abstract Result open(Long activity, Enum<Constants.ActivityState> currentStatus);

    public abstract Result doing(Long activity, Enum<Constants.ActivityState> currentStatus);

}

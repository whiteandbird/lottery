package com.itwang.lottery.domain.activity.service.stateflow.impl;

import com.itwang.lottery.domain.activity.service.stateflow.IStateHandler;
import com.itwang.lottery.domain.activity.service.stateflow.StateConfig;
import it.comwang.lottery.common.Constants;
import it.comwang.lottery.common.Result;
import org.springframework.stereotype.Service;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  18:38
 */
@Service
public class StateHandlerImpl extends StateConfig implements IStateHandler {
    @Override
    public Result arraignment(Long activity, Enum<Constants.ActivityState> currentStatus) {
        return abstractStateMap.get(currentStatus).arraignment(activity, currentStatus);
    }


    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return abstractStateMap.get(currentStatus).checkPass(activityId, currentStatus);
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return abstractStateMap.get(currentStatus).checkRefuse(activityId, currentStatus);
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return abstractStateMap.get(currentStatus).checkRevoke(activityId, currentStatus);
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return abstractStateMap.get(currentStatus).close(activityId, currentStatus);
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return abstractStateMap.get(currentStatus).open(activityId, currentStatus);
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return abstractStateMap.get(currentStatus).doing(activityId, currentStatus);
    }
}

package com.itwang.lottery.domain.activity.service.stateflow.event;

import com.itwang.lottery.domain.activity.service.stateflow.AbstractState;
import it.comwang.lottery.common.Constants;
import it.comwang.lottery.common.Result;
import org.springframework.stereotype.Component;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  17:11
 */
@Component
public class CloseState extends AbstractState {
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动关闭不可提审");
    }

    @Override
    public Result checkPass(Long activity, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildErrorResult("活动关闭不可审核通过");
    }

    @Override
    public Result checkRefuse(Long activity, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildErrorResult("活动关闭不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activity, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildErrorResult("活动关闭不可撤销审核");
    }

    @Override
    public Result close(Long activity, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildErrorResult("活动不可重复管不");
    }

    @Override
    public Result open(Long activity, Enum<Constants.ActivityState> currentStatus) {
        boolean open = activityRepository.alterStatus(activity, currentStatus, Constants.ActivityState.OPEN);
        return open ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(), "活动开启成功") : Result.buildErrorResult("活动开启失败");
    }

    @Override
    public Result doing(Long activity, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildErrorResult("活动关闭不可变成活动中");
    }
}

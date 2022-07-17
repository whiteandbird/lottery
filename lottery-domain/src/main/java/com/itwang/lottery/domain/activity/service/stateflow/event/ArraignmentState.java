package com.itwang.lottery.domain.activity.service.stateflow.event;

import com.itwang.lottery.domain.activity.service.stateflow.AbstractState;
import it.comwang.lottery.common.Constants;
import it.comwang.lottery.common.Result;
import org.springframework.stereotype.Component;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  16:59
 */
@Component
public class ArraignmentState extends AbstractState {
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "待审核状态不可重复提审");
    }

    @Override
    public Result checkPass(Long activity, Enum<Constants.ActivityState> currentStatus) {
        boolean pass = activityRepository.alterStatus(activity, currentStatus, Constants.ActivityState.PASS);
        return pass ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核通过") : Result.buildErrorResult("活动变更失败");
    }

    @Override
    public Result checkRefuse(Long activity, Enum<Constants.ActivityState> currentStatus) {
        boolean pass = activityRepository.alterStatus(activity, currentStatus, Constants.ActivityState.REFUSE);
        return pass ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动拒绝通过") : Result.buildErrorResult("活动变更失败");
    }

    @Override
    public Result checkRevoke(Long activity, Enum<Constants.ActivityState> currentStatus) {
        boolean pass = activityRepository.alterStatus(activity, currentStatus, Constants.ActivityState.EDIT);
        return pass ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核撤回到编辑中") : Result.buildErrorResult("活动变更失败");
    }

    @Override
    public Result close(Long activity, Enum<Constants.ActivityState> currentStatus) {
        boolean pass = activityRepository.alterStatus(activity, currentStatus, Constants.ActivityState.CLOSE);
        return pass ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核关闭完成") : Result.buildErrorResult("活动变更失败");
    }

    @Override
    public Result open(Long activity, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "非关闭活动不可开启");
    }

    @Override
    public Result doing(Long activity, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "待审核活动不可执行活动中变更");
    }
}

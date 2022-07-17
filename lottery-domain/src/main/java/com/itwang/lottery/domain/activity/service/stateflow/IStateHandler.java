package com.itwang.lottery.domain.activity.service.stateflow;

import it.comwang.lottery.common.Constants;
import it.comwang.lottery.common.Result;

public interface IStateHandler {

    Result arraignment(Long activity, Enum<Constants.ActivityState> currentStatus);

    Result checkPass(Long activity, Enum<Constants.ActivityState> currentStatus);

    Result checkRefuse(Long activity, Enum<Constants.ActivityState> currentStatus);

    Result checkRevoke(Long activity, Enum<Constants.ActivityState> currentStatus);

    Result close(Long activity, Enum<Constants.ActivityState> currentStatus);

    Result open(Long activity, Enum<Constants.ActivityState> currentStatus);

    Result doing(Long activity, Enum<Constants.ActivityState> currentStatus);


}

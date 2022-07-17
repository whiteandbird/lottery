package com.itwang.lottery.domain.activity.service.stateflow;

import com.itwang.lottery.domain.activity.service.stateflow.event.*;
import it.comwang.lottery.common.Constants;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  18:36
 */
public class StateConfig {

    @Resource
    private ArraignmentState arraignmentState;
    @Resource
    private CloseState closeState;
    @Resource
    private DoingState doingState;
    @Resource
    private EditingState editingState;
    @Resource
    private OpenState openState;
    @Resource
    private PassState passState;
    @Resource
    private RefuseState refuseState;


    protected Map<Enum<Constants.ActivityState>, AbstractState> abstractStateMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void init(){
        abstractStateMap.put(Constants.ActivityState.ARRAIGNMENT, arraignmentState);
        abstractStateMap.put(Constants.ActivityState.CLOSE, closeState);
        abstractStateMap.put(Constants.ActivityState.DOING, doingState);
        abstractStateMap.put(Constants.ActivityState.EDIT, editingState);
        abstractStateMap.put(Constants.ActivityState.OPEN, openState);
        abstractStateMap.put(Constants.ActivityState.PASS, passState);
        abstractStateMap.put(Constants.ActivityState.REFUSE, refuseState);
    }
}

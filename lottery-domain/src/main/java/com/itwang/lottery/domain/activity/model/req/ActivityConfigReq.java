package com.itwang.lottery.domain.activity.model.req;

import com.itwang.lottery.domain.activity.model.aggregates.ActivityConfigRich;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  13:51
 */
public class ActivityConfigReq {

    private Long activityId;


    private ActivityConfigRich activityConfigRich;


    public ActivityConfigReq(){

    }

    public ActivityConfigReq(Long activityId, ActivityConfigRich activityConfigRich) {
        this.activityId = activityId;
        this.activityConfigRich = activityConfigRich;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public ActivityConfigRich getActivityConfigRich() {
        return activityConfigRich;
    }

    public void setActivityConfigRich(ActivityConfigRich activityConfigRich) {
        this.activityConfigRich = activityConfigRich;
    }



}

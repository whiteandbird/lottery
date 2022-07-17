package com.itwang.lottery.domain.activity.service.deploy;

import com.itwang.lottery.domain.activity.model.req.ActivityConfigReq;

public interface IActivityDeploy {

    /**
     * create activity
     * @param req
     */
    void createActivity(ActivityConfigReq req);

    void updateActivity(ActivityConfigReq req);
}

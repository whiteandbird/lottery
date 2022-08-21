package com.itwang.lottery.domain.activity.service.partake;

import com.itwang.lottery.domain.activity.model.req.PartakeReq;
import com.itwang.lottery.domain.activity.model.vo.ActivityBillVO;
import com.itwang.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:18  22:45
 */
public class ActivityPartakeSupport {
    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq partakeReq){
        // 用户参与哪个活动 什么时候
        return activityRepository.queryActivityBill(partakeReq);
    }
}

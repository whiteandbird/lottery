package com.itwang.lottery.interfaces;

import com.itwang.lottery.infrastructure.dao.IActivityDao;
import com.itwang.lottery.infrastructure.po.Activity;
import com.itwang.lottery.rpc.IActivityBooth;
import com.itwang.lottery.rpc.dto.ActivityDto;
import com.itwang.lottery.rpc.req.ActivityReq;
import com.itwang.lottery.rpc.res.ActivityRes;
import it.comwang.lottery.common.Constants;
import it.comwang.lottery.common.Result;
import org.apache.dubbo.config.annotation.Service;


import javax.annotation.Resource;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:25  18:14
 */
@Service
public class ActivityBoothImpl implements IActivityBooth {
    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()), activityDto);
    }
}

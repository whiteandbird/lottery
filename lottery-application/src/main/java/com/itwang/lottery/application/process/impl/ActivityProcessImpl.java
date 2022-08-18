package com.itwang.lottery.application.process.impl;

import com.itwang.lottery.application.process.IActivityProcess;
import com.itwang.lottery.application.process.req.DrawProcessReq;
import com.itwang.lottery.application.process.res.DrawProcessResult;
import com.itwang.lottery.domain.activity.model.req.PartakeReq;
import com.itwang.lottery.domain.activity.model.res.PartakeResult;
import com.itwang.lottery.domain.activity.service.partake.IActivityPartake;
import com.itwang.lottery.domain.strategy.model.req.DrawReq;
import com.itwang.lottery.domain.strategy.model.res.DrawResult;
import com.itwang.lottery.domain.strategy.model.vo.DrawAwardInfo;
import com.itwang.lottery.domain.strategy.service.draw.IDrawExec;
import com.itwang.lottery.domain.support.ids.IdGenerator;
import it.comwang.lottery.common.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:18  23:36
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {


    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private Map<Constants.Ids, IdGenerator> idGeneratorMap;

    @Override
    public DrawProcessResult doDrawProcess(DrawProcessReq req) {
        // 是谁 参与哪个活动 参与的活动是使用什么策略
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));

        if(!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())){
            return new DrawProcessResult(partakeResult.getCode(), partakeResult.getInfo());
        }

        Long strategyId = partakeResult.getStrategyId();
        Long takeId = partakeResult.getTakeId();

        // 进行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), strategyId, String.valueOf(takeId)));
        if(Constants.DrawState.FAIL.equals(drawResult.getDrawState())){
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());
        }
        DrawAwardInfo drawAwardInfo = drawResult.getDrawAwardInfo();
        // TODO 结果落库
//        activityPartake


        return null;
    }
}

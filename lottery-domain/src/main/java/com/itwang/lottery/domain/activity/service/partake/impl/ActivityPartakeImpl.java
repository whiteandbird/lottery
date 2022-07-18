package com.itwang.lottery.domain.activity.service.partake.impl;

import com.itwang.lottery.domain.activity.model.req.PartakeReq;
import com.itwang.lottery.domain.activity.model.vo.ActivityBillVO;
import com.itwang.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.itwang.lottery.domain.activity.service.partake.BaseActivityPartake;
import com.itwang.lottery.domain.support.ids.IdGenerator;
import com.wang.middleware.db.router.strategy.IDBRouterStrategy;
import it.comwang.lottery.common.Constants;
import it.comwang.lottery.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:19  0:02
 */

@Service
public class ActivityPartakeImpl extends BaseActivityPartake {
    private Logger logger = LoggerFactory.getLogger(ActivityPartakeImpl.class);

    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;

    @Resource
    private IDBRouterStrategy idbRouterStrategy;

    @Resource
    private Map<Constants.Ids, IdGenerator> idsIdGeneratorMap;

    /**
     * 此处注入应改是mybatis里面的那个
     */
    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    protected Result checkActivityBill(PartakeReq partakeReq, ActivityBillVO activityBillVO) {
        if(!Constants.ActivityState.DOING.getCode().equals(activityBillVO.getState())){
            logger.warn("当前活动状态非可用 state:{}", activityBillVO.getState());
            return Result.buildErrorResult("当前活动状态非可用");
        }
        if(activityBillVO.getBeginDateTime().after(partakeReq.getPartakeDate()) || activityBillVO.getEndDateTime().before(partakeReq.getPartakeDate())){
            logger.warn("当前时间范围非可用 活动开始时间:{} 结束时间:{} 参与时间:{}", activityBillVO.getBeginDateTime(), activityBillVO.getEndDateTime(), partakeReq.getPartakeDate());
            return Result.buildErrorResult("当前时间范围非可用");
        }
        if(activityBillVO.getStockSurplusCount() <= 0){
            logger.info("可用库存已经使用完毕:surplusCount : {}", activityBillVO.getStockSurplusCount());
            return Result.buildErrorResult("可用库存已经使用完毕");
        }
        if(activityBillVO.getUserTakeLeftCount() <= 0){
            logger.info("个人领取次数非可用 userTakeCountLeft: {}", activityBillVO.getUserTakeLeftCount());
            return Result.buildErrorResult("个人领取次数已经完毕");
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result subtractionActivityStock(PartakeReq partakeReq) {
        int result = activityRepository.subtractionActivityStock(partakeReq.getActivityId());
        if(0 == result){
            logger.warn("扣减活动库存失败 activityId:{}", partakeReq.getActivityId());
            return Result.buildErrorResult("扣减活动库存失败");
        }
        return Result.buildSuccessResult();

    }

    @Override
    protected Result grabActivity(PartakeReq partakeReq, ActivityBillVO billVO) {
        return null;
    }
}

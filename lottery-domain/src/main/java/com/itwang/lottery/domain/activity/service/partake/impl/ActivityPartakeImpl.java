package com.itwang.lottery.domain.activity.service.partake.impl;

import com.itwang.lottery.domain.activity.model.req.PartakeReq;
import com.itwang.lottery.domain.activity.model.vo.ActivityBillVO;
import com.itwang.lottery.domain.activity.model.vo.DrawOrderVO;
import com.itwang.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.itwang.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.itwang.lottery.domain.activity.service.partake.BaseActivityPartake;
import com.itwang.lottery.domain.support.ids.IdGenerator;
import com.wang.middleware.db.router.strategy.IDBRouterStrategy;
import it.comwang.lottery.common.Constants;
import it.comwang.lottery.common.Result;
import org.apache.tomcat.util.bcel.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
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
    protected Result grabActivity(PartakeReq partakeReq, ActivityBillVO billVO, Long takeId) {
        try{
            // 设置上下文
            // 加入活动
            idbRouterStrategy.doRouter(partakeReq.getuId());
            return transactionTemplate.execute( status -> {
                try{
                    // 参与一次抽奖   那么用户抽奖次数减1  查询当前是否有可用抽奖机会
                    int updateCount = userTakeActivityRepository.subtractionLeftCount(billVO.getActivityId(), billVO.getActivityName(), billVO.getTakeCount(), billVO.getUserTakeLeftCount(), partakeReq.getuId(), partakeReq.getPartakeDate());
                    if(0 == updateCount){
                        // 抽奖次数归0
                        status.setRollbackOnly();
                        logger.error("领取活动 扣减个人记录失败 activityId:{} uId:{}", partakeReq.getActivityId(), partakeReq.getuId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }

                    // 写入领取活动记录   用户还有抽奖机会
                    // 记录参与活动的流水
                    userTakeActivityRepository.takeActivity(billVO.getActivityId(), billVO.getActivityName(),billVO.getStrategyId(), billVO.getTakeCount(), billVO.getUserTakeLeftCount(), partakeReq.getuId(), partakeReq.getPartakeDate(), takeId);

                }catch (DuplicateKeyException e){
                    status.setRollbackOnly();
                    logger.error("领取活动 唯一索引冲突 activityId {} uid {}", partakeReq.getActivityId(), partakeReq.getuId());
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildResult(Constants.ResponseCode.SUCCESS);
            });
        }finally {
            idbRouterStrategy.clear();
        }
    }

    @Override
    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId, uId);
    }

    @Override
    public Result recordDrawOrder(DrawOrderVO res) {
        try{
            idbRouterStrategy.doRouter(res.getuId());
            return transactionTemplate.execute(status ->{
                try{
                    System.out.println(status);
                    // 锁定活动领取记录
                    int lockCount = userTakeActivityRepository.lockTakeActivity(res.getuId(), res.getActivityId(), res.getTakeId());
                    if(0 == lockCount){
                        status.setRollbackOnly();
                        logger.error("记录中将单, 个人参与活动抽奖已消耗完");
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }
                }catch (DuplicateKeyException e){
                    logger.error("唯一键冲突",e);
                    status.setRollbackOnly();
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
                // 保存抽奖信息
            });
        }finally {
            idbRouterStrategy.clear();
        }
    }
}

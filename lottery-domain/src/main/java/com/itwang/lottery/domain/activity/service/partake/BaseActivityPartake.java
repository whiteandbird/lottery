package com.itwang.lottery.domain.activity.service.partake;

import com.itwang.lottery.domain.activity.model.req.PartakeReq;
import com.itwang.lottery.domain.activity.model.res.PartakeResult;
import com.itwang.lottery.domain.activity.model.vo.ActivityBillVO;
import com.itwang.lottery.domain.activity.model.vo.DrawOrderVO;
import com.itwang.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.itwang.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.itwang.lottery.domain.support.ids.IdGenerator;
import com.wang.middleware.db.router.strategy.IDBRouterStrategy;
import it.comwang.lottery.common.Constants;
import it.comwang.lottery.common.Result;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 活动领取模板抽象类
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:18  22:57
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake {


    @Resource
    private Map<Constants.Ids, IdGenerator> idGeneratorMap;


    @Override
    public PartakeResult doPartake(PartakeReq req) {
        // 用户请求参与某个活动  该活动有可能参与有可能没被参与
        // 是否存在未执行完的抽奖活动
        // 拿到了该策略的参与id 以及 应该使用的策略
        UserTakeActivityVO userTakeActivityVO = this.queryNoConsumedTakeActivityOrder(req.getActivityId(), req.getuId());
        if(userTakeActivityVO != null){
            return buildPartakeResult(userTakeActivityVO.getStrategyId(), userTakeActivityVO.getTakeId());
        }
        // 查询活动账单
        // 用户之前没参与该活动
        // 则需要去获取到该活动  该活动该用户的剩余可用库存 以及可参加次数
        ActivityBillVO activityBillVO = queryActivityBill(req);

        // 活动信息校验处理【活动库存 状态 日期 个人参与次数】  对上面查出来的活动进行相关的校验
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())){
            return new PartakeResult(checkResult.getCode(), checkResult.getInfo());
        }
        // 扣减库存 扣减活动库存  活动限制人数参与   每个人一旦参与活动就会减去一个参与量 即活动库存
        Result subtractionActivityStockResult = this.subtractionActivityStock(req);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityStockResult.getCode())){
            return new PartakeResult(subtractionActivityStockResult.getCode(), subtractionActivityStockResult.getInfo());
        }
        // 校验通过 扣减活动库存成功  那么可以执行策略了
        long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        Result grabResult = this.grabActivity(req, activityBillVO, takeId);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityStockResult.getCode())){
            return new PartakeResult(grabResult.getCode(), grabResult.getInfo());
        }


        PartakeResult partakeResult = new PartakeResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        partakeResult.setStrategyId(activityBillVO.getStrategyId());
        partakeResult.setTakeId(takeId);

        return partakeResult;


    }

    /**
     * 封装结果【返回的策略ID，用于继续完成抽奖步骤】
     *
     * @param strategyId 策略ID
     * @param takeId     领取ID
     * @return 封装结果
     */
    private PartakeResult buildPartakeResult(Long strategyId, Long takeId) {
        PartakeResult partakeResult = new PartakeResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        return partakeResult;
    }


    protected  abstract UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);

    protected abstract Result checkActivityBill(PartakeReq partakeReq, ActivityBillVO activityBillVO);


    protected abstract Result subtractionActivityStock(PartakeReq partakeReq);


    protected  abstract Result grabActivity(PartakeReq partakeReq, ActivityBillVO billVO, Long takeId);
}

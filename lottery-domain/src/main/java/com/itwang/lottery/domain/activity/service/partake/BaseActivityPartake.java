package com.itwang.lottery.domain.activity.service.partake;

import com.itwang.lottery.domain.activity.model.req.PartakeReq;
import com.itwang.lottery.domain.activity.model.res.PartakeResult;
import com.itwang.lottery.domain.activity.model.vo.ActivityBillVO;
import it.comwang.lottery.common.Constants;
import it.comwang.lottery.common.Result;

/**
 * 活动领取模板抽象类
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:18  22:57
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake {


    @Override
    public PartakeResult doPartake(PartakeReq req) {
        // 查询活动账单
        ActivityBillVO activityBillVO = queryActivityBill(req);

        // 活动信息校验处理【活动库存 状态 日期 个人参与次数】
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())){
            return new PartakeResult(checkResult.getCode(), checkResult.getInfo());
        }
        // 扣减库存
        Result subtractionActivityStockResult = this.subtractionActivityStock(req);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityStockResult.getCode())){
            return new PartakeResult(subtractionActivityStockResult.getCode(), subtractionActivityStockResult.getInfo());
        }

        Result grabResult = this.grabActivity(req, activityBillVO);
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityStockResult.getCode())){
            return new PartakeResult(grabResult.getCode(), grabResult.getInfo());
        }


        PartakeResult partakeResult = new PartakeResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        partakeResult.setStrategyId(activityBillVO.getStrategyId());

        return partakeResult;


    }

    protected abstract Result checkActivityBill(PartakeReq partakeReq, ActivityBillVO activityBillVO);


    protected abstract Result subtractionActivityStock(PartakeReq partakeReq);


    protected  abstract Result grabActivity(PartakeReq partakeReq, ActivityBillVO billVO);
}

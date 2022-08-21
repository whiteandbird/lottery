package com.itwang.lottery.domain.strategy.service.draw;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.itwang.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.itwang.lottery.domain.strategy.model.req.DrawReq;
import com.itwang.lottery.domain.strategy.model.res.DrawResult;
import com.itwang.lottery.domain.strategy.model.vo.*;
import com.itwang.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import it.comwang.lottery.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  15:16
 */
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec{

    private Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);


    @Override
    public DrawResult doDrawExec(DrawReq req) {
        // 奖品跟策略绑定
        // 策略id 策略 以及策略明细
        StrategyRich strategyRich = queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();
        List<StrategyDetailBriefVO> strategyDetailList = strategyRich.getStrategyDetailList();

        // 为抽奖进行后续铺垫 准备数据
        this.checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(), strategyDetailList);

        // 用来进行风控
        List<String> excludeAwardIds = this.queryExcludeAwardIds(req.getStrategyId());


        String awardId =  this.drawAlgorithm(req.getStrategyId(), drawAlgorithmMap.get(strategy.getStrategyMode()), excludeAwardIds);

        return buildDrawResult(req.getuId(), req.getStrategyId(), awardId);
    }

    protected abstract String  drawAlgorithm(Long strategyId, IDrawAlgorithm iDrawAlgorithm, List<String> excludeAwardIds);

    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList){
        // 采用何种算法
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        // 初始化奖品id 以及奖品的概率
        for(StrategyDetailBriefVO strategyDetail : strategyDetailList){
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));

        }
        // 非单体策略 不需要进行初始化
        if(Constants.StrategyMode.ENTIRETY.getCode().equals(strategyMode)){
            // 把奖品以及概率放进去  供之后使用
            if(CollUtil.isNotEmpty(strategyDetailList)){
                drawAlgorithm.putAwardRateInfoIfAbsent(strategyId, awardRateInfoList);
            }
        }
        // 单体策略抽奖用到了斐波拉契算法  将奖品放在某个区间   概率如果在那直接取出来
        if(!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) return;
        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
        if(existRateTuple) return;

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
    }


    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId){
        if(null == awardId){
            logger.info("执行抽奖策略完成【未中奖】： 用户：{} 策略ID：{}", uId, strategyId);
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }

        AwardBriefVO award = queryAwardInfoByAwardId(awardId);

        DrawAwardInfo awardInfo = new DrawAwardInfo(award.getAwardId(), award.getAwardType(), award.getAwardName(), award.getAwardContent());
        logger.info("执行策略抽奖完成【已中奖】 用户 ：{} 策略ID： {}, 礼品:{}", uId, strategyId, JSONObject.toJSONString(award));
        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), awardInfo);
    }
}

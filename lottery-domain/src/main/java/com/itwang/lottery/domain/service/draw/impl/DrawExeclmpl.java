package com.itwang.lottery.domain.service.draw.impl;

import com.itwang.lottery.domain.model.aggregates.StrategyRich;
import com.itwang.lottery.domain.model.req.DrawReq;
import com.itwang.lottery.domain.model.res.DrawResult;
import com.itwang.lottery.domain.repository.IStrategyRepository;
import com.itwang.lottery.domain.service.algorithm.IDrawAlgorithm;
import com.itwang.lottery.domain.service.draw.DrawBase;
import com.itwang.lottery.domain.service.draw.IDrawExec;
import com.itwang.lottery.infrastructure.po.Award;
import com.itwang.lottery.infrastructure.po.Strategy;
import com.itwang.lottery.infrastructure.po.StrategyDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  15:22
 */
@Service
public class DrawExeclmpl extends DrawBase implements IDrawExec {

    private Logger logger = LoggerFactory.getLogger(DrawExeclmpl.class);

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        logger.info("执行策略抽奖开始, strategyId : {}", req.getStrategyId());
        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();
        List<StrategyDetail> strategyDetailList = strategyRich.getStrategyDetailList();
        checkAndInitRateData(req.getStrategyId(),strategy.getStrategyMode(), strategyDetailList);


        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        String awardId = drawAlgorithm.randomDraw(req.getStrategyId(), new ArrayList<>());
        Award award = strategyRepository.queryAwardInfo(awardId);
        logger.info("执行抽奖策略完成， 用户:{}, 奖品: {}", req.getuId(), award.getAwardId());
        return  new DrawResult(req.getuId(), req.getStrategyId(), awardId, award.getAwardName());
    }
}

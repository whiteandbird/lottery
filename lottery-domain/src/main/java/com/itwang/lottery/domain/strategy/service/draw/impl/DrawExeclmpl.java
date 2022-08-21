package com.itwang.lottery.domain.strategy.service.draw.impl;

import com.alibaba.fastjson.JSON;
import com.itwang.lottery.domain.strategy.repository.IStrategyRepository;
import com.itwang.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.itwang.lottery.domain.strategy.service.draw.AbstractDrawBase;
import com.itwang.lottery.domain.strategy.service.draw.IDrawExec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  15:22
 */
@Service
public class DrawExeclmpl extends AbstractDrawBase implements IDrawExec {

    private Logger logger = LoggerFactory.getLogger(DrawExeclmpl.class);

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm iDrawAlgorithm, List<String> excludeAwardIds) {
        // 根据算法进行抽奖
        String awardId = iDrawAlgorithm.randomDraw(strategyId, excludeAwardIds);
        // 如果没抽到
        if(null == awardId){
            return null;
        }

        // 抽到了需要减去相应奖品的库存
        boolean isSuccess = strategyRepository.deductStock(strategyId, awardId);

        // 返回结果，库存扣减成功返回奖品ID，否则返回NULL 「在实际的业务场景中，如果中奖奖品库存为空，则会发送兜底奖品，比如各类券」
        return isSuccess ? awardId : null;


    }

    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {
        List<String> noStockList =  strategyRepository.queryNostockStrategyAwardList(strategyId);
        logger.info("执行抽奖策略 strategyId :{} 无库存排除奖品ID集合 list:{}", strategyId, JSON.toJSONString(noStockList));
        return noStockList;

    }
}

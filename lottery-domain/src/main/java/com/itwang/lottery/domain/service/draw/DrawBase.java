package com.itwang.lottery.domain.service.draw;

import com.itwang.lottery.domain.model.vo.AwardRateInfo;
import com.itwang.lottery.domain.service.algorithm.IDrawAlgorithm;
import com.itwang.lottery.infrastructure.po.StrategyDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  15:16
 */
public class DrawBase extends DrawConfig{

    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList){
        if(1 != strategyMode) return;

        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
        if(existRateTuple) return;

        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for(StrategyDetail strategyDetail : strategyDetailList){
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));

        }

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
    }
}

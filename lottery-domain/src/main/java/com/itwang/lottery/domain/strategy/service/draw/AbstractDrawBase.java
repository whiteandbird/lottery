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
        StrategyRich strategyRich = queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();
        List<StrategyDetailBriefVO> strategyDetailList = strategyRich.getStrategyDetailList();

        this.checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(), strategyDetailList);

        List<String> excludeAwardIds = this.queryExcludeAwardIds(req.getStrategyId());


        String awardId =  this.drawAlgorithm(req.getStrategyId(), drawAlgorithmMap.get(strategy.getStrategyMode()), excludeAwardIds);

        return buildDrawResult(req.getuId(), req.getStrategyId(), awardId);
    }

    protected abstract String  drawAlgorithm(Long strategyId, IDrawAlgorithm iDrawAlgorithm, List<String> excludeAwardIds);

    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList){
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for(StrategyDetailBriefVO strategyDetail : strategyDetailList){
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));

        }
        // ??????????????? ????????????????????????
        if(Constants.StrategyMode.ENTIRETY.getCode().equals(strategyMode)){
            if(CollUtil.isNotEmpty(strategyDetailList)){
                drawAlgorithm.putAwardRateInfoIfAbsent(strategyId, awardRateInfoList);
            }
        }
        if(!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) return;
        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
        if(existRateTuple) return;

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
    }


    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId){
        if(null == awardId){
            logger.info("?????????????????????????????????????????? ?????????{} ??????ID???{}", uId, strategyId);
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }

        AwardBriefVO award = queryAwardInfoByAwardId(awardId);

        DrawAwardInfo awardInfo = new DrawAwardInfo(award.getAwardId(), award.getAwardType(), award.getAwardName(), award.getAwardContent());
        logger.info("??????????????????????????????????????? ?????? ???{} ??????ID??? {}, ??????:{}", uId, strategyId, JSONObject.toJSONString(award));
        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), awardInfo);
    }
}

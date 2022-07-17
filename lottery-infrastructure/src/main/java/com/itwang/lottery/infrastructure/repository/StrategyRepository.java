package com.itwang.lottery.infrastructure.repository;

import com.itwang.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.itwang.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.itwang.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.itwang.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import com.itwang.lottery.domain.strategy.repository.IStrategyRepository;
import com.itwang.lottery.infrastructure.dao.IAwardDao;
import com.itwang.lottery.infrastructure.dao.IStrategyDao;
import com.itwang.lottery.infrastructure.dao.IStrategyDetailDao;
import com.itwang.lottery.infrastructure.po.Award;
import com.itwang.lottery.infrastructure.po.Strategy;
import com.itwang.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  10:39
 */
@Component
public class StrategyRepository  implements IStrategyRepository {
    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy =  strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList =strategyDetailDao.queryStrategyDetail(strategyId);
        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        BeanUtils.copyProperties(strategy, strategyBriefVO);

        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
            BeanUtils.copyProperties(strategyDetail, strategyDetailBriefVO);
            strategyDetailBriefVOList.add(strategyDetailBriefVO);
        }
        return new StrategyRich(strategyId, strategyBriefVO, strategyDetailBriefVOList);
    }

    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {
        Award award = awardDao.queryAwardInfo(awardId);
        AwardBriefVO awardBriefVO = new AwardBriefVO();
        awardBriefVO.setAwardId(award.getAwardId());
        awardBriefVO.setAwardType(award.getAwardType());
        awardBriefVO.setAwardName(award.getAwardName());
        awardBriefVO.setAwardContent(award.getAwardContent());

        return awardBriefVO;
    }


    @Override
    public List<String> queryNostockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        return strategyDetailDao.deductStock(strategyId, awardId);
    }
}

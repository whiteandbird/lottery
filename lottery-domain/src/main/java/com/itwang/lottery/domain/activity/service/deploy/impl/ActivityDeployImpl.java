package com.itwang.lottery.domain.activity.service.deploy.impl;

import com.itwang.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import com.itwang.lottery.domain.activity.model.req.ActivityConfigReq;
import com.itwang.lottery.domain.activity.model.vo.ActivityVO;
import com.itwang.lottery.domain.activity.model.vo.AwardVO;
import com.itwang.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.itwang.lottery.domain.activity.model.vo.StrategyVO;
import com.itwang.lottery.domain.activity.repository.IActivityRepository;
import com.itwang.lottery.domain.activity.service.deploy.IActivityDeploy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  15:26
 */
@Service
public class ActivityDeployImpl implements IActivityDeploy {

    private Logger logger = LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    private IActivityRepository activityRepository;

    @Transactional
    @Override
    public void createActivity(ActivityConfigReq req) {
        logger.info("开始创建活动配置 活动activityId:{}", req.getActivityId());
        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();
        try{


            List<AwardVO> awardList = activityConfigRich.getAwardList();
            activityRepository.addAward(awardList);

            StrategyVO strategy = activityConfigRich.getStrategy();
            activityRepository.addStrategy(strategy);

            ActivityVO activity = activityConfigRich.getActivity();
            activityRepository.addActivity(activity);

            List<StrategyDetailVO> strategyDetailList = strategy.getStrategyDetailList();
            activityRepository.addStrategyDetailList(strategyDetailList);
            logger.info("创建成功");
        }catch (DuplicateKeyException e){
            logger.info("创建活动配置失",e);
            throw e;
        }
    }

    @Override
    public void updateActivity(ActivityConfigReq req) {

    }
}

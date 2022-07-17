package com.itwang.lottery.infrastructure.repository;

import com.itwang.lottery.domain.activity.model.vo.*;
import com.itwang.lottery.domain.activity.repository.IActivityRepository;
import com.itwang.lottery.infrastructure.dao.IActivityDao;
import com.itwang.lottery.infrastructure.dao.IAwardDao;
import com.itwang.lottery.infrastructure.dao.IStrategyDao;
import com.itwang.lottery.infrastructure.dao.IStrategyDetailDao;
import com.itwang.lottery.infrastructure.po.Activity;
import com.itwang.lottery.infrastructure.po.Award;
import com.itwang.lottery.infrastructure.po.Strategy;
import com.itwang.lottery.infrastructure.po.StrategyDetail;
import it.comwang.lottery.common.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  13:47
 */
@Component
public class ActivityRepository implements IActivityRepository {


    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();
        BeanUtils.copyProperties(activity, req);
        activityDao.insert(req);
    }

    @Override
    public void addAward(List<AwardVO> awardList) {
        List<Award> reqs = new ArrayList<>();
        for(AwardVO item : awardList){
            Award award = new Award();
            BeanUtils.copyProperties(item, award);
            reqs.add(award);
        }
        awardDao.insertList(reqs);
    }

    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();
        BeanUtils.copyProperties(strategy, req);
        strategyDao.insert(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {

        List<StrategyDetail> req = strategyDetailList.stream().map(e ->
        {
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtils.copyProperties(e, strategyDetail);
            return strategyDetail;
        }).collect(Collectors.toList());
        strategyDetailDao.insertList(req);
    }

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((Constants.ActivityState) beforeState).getCode(), ((Constants.ActivityState)afterState).getCode());
        int count = activityDao.alterState(alterStateVO);
        return 1 == count;
    }
}

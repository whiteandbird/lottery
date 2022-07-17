package com.itwang.lottery.domain.award.service.goods;

import com.itwang.lottery.domain.award.repository.IAwardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  9:57
 */
public class DistributionBase {
    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

    @Resource
    private IAwardRepository awardRepository;

    protected void updateUserAwardState(String uId,String orderId, String awardId, Integer awardState, String awardStateInfo){
        // TODO 后期增加分库分表之后，用户个人的抽奖记录表中奖品发奖状态
        logger.info("后期增加分库分表之后，用户个人的抽奖记录表中奖品发奖状态");
    }
}

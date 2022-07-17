package com.itwang.lottery;

import com.alibaba.fastjson.JSON;
import com.itwang.lottery.domain.award.model.req.GoodReq;
import com.itwang.lottery.domain.award.model.res.DistributionRes;
import com.itwang.lottery.domain.award.service.factory.DistributuionGoodsFactory;
import com.itwang.lottery.domain.award.service.goods.IDistributionGoods;
import com.itwang.lottery.domain.strategy.model.req.DrawReq;
import com.itwang.lottery.domain.strategy.model.res.DrawResult;
import com.itwang.lottery.domain.strategy.model.vo.DrawAwardInfo;
import com.itwang.lottery.domain.strategy.service.draw.IDrawExec;
import it.comwang.lottery.common.Constants;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  11:08
 */
@RunWith(
        SpringRunner.class
)
@SpringBootTest
public class IGoodFactoryTest {

    private Logger logger  = LoggerFactory.getLogger(IGoodFactoryTest.class);

    @Resource
    private IDrawExec drawExec;

    @Resource
    private IDistributionGoods distributionGoods;

    @Resource
    private DistributuionGoodsFactory distributuionGoodsFactory;


    @Test
    @DisplayName("测试奖品")
    public void test_Award(){
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq("小哥", 10001L));
        Integer drawState = drawResult.getDrawState();
        if(Constants.AwardState.FAILURE.getCode().equals(drawState)){
            logger.info("未中奖");
            return;
        }
        DrawAwardInfo drawAwardInfo =  drawResult.getDrawAwardInfo();
        GoodReq goodReq  = new GoodReq(drawResult.getuId(), "ORDER30001",drawAwardInfo.getAwardId(), drawAwardInfo.getAwardName(), drawAwardInfo.getAwardContent());
        IDistributionGoods distributionGoodsService = distributuionGoodsFactory.getDistributionGoodsService(drawAwardInfo.getAwardType());
        DistributionRes distributionRes = distributionGoodsService.doDistribution(goodReq);
        logger.info("测试结果:{}", JSON.toJSONString(distributionRes));
    }
}

package com.itwang.lottery.test.domain;

import com.alibaba.fastjson.JSON;
import com.itwang.lottery.application.process.IActivityProcess;
import com.itwang.lottery.application.process.req.DrawProcessReq;
import com.itwang.lottery.application.process.res.DrawProcessResult;
import com.itwang.lottery.domain.activity.model.req.PartakeReq;
import com.itwang.lottery.domain.activity.service.partake.IActivityPartake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:21  15:16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityProcessTest {

    private Logger logger = LoggerFactory.getLogger(ActivityProcessTest.class);

    @Resource
    private IActivityProcess activityProcess;


    @Test
    public void test(){
        DrawProcessReq req = new DrawProcessReq();
        req.setuId("fustack");
        req.setActivityId(100001L);

        DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(req);
        logger.info("请求入参:{}", JSON.toJSONString(req));
        logger.info("测试结果:{}", JSON.toJSONString(drawProcessResult));
    }
}

package com.itwang.lottery.rpc;

import com.itwang.lottery.LotteryApplication;
import com.itwang.lottery.rpc.req.ActivityReq;
import com.itwang.lottery.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:25  18:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LotteryApplication.class)
public class RpcTest {

    private Logger logger = LoggerFactory.getLogger(RpcTest.class);


    @Reference(interfaceClass = IActivityBooth.class, url = "dubbo://127.0.0.1:20880")
    private IActivityBooth activityBooth;


    @Test
    public void testRpc(){
        ActivityReq activityReq = new ActivityReq();
        activityReq.setActivityId(1l);
        ActivityRes activityRes = activityBooth.queryActivityById(activityReq);
        logger.info("result:"+activityRes);
    }
}

package com.itwang.lottery;

import com.itwang.lottery.infrastructure.dao.IActivityDao;
import com.itwang.lottery.infrastructure.po.Activity;
import com.itwang.lottery.rpc.IActivityBooth;
import com.itwang.lottery.rpc.req.ActivityReq;
import com.itwang.lottery.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:25  18:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcTest {

    private Logger logger = LoggerFactory.getLogger(RpcTest.class);

    @Resource
    private IActivityDao activityDao;

    @Reference(interfaceClass = IActivityBooth.class, url = "dubbo://127.0.0.1:20880")
    private IActivityBooth activityBooth;


    @Test
    public void testRpc(){
        ActivityReq activityReq = new ActivityReq();
        activityReq.setActivityId(1l);
        ActivityRes activityRes = activityBooth.queryActivityById(activityReq);
        logger.info("result:"+activityRes);
    }

    @Test
    public void testInsert(){
        Activity req = new Activity();
        req.setId(2l);
        req.setActivityDesc("描述2");
        req.setActivityName("this is name");
        req.setBeginDateTime(new Date());
        req.setEndDateTime(new Date());
        req.setCreator("wang");
        req.setState(1);
        req.setStockCount(5);
        req.setTakeCount(5);
        activityDao.insert(req);
    }
}

package com.itwang.lottery;

import com.itwang.lottery.domain.support.ids.IdGenerator;
import it.comwang.lottery.common.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:17  9:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GeneratorTest {

    private Logger logger = LoggerFactory.getLogger(GeneratorTest.class);


    @Resource
    private Map<Constants.Ids, IdGenerator> idsIdGeneratorMap;

    @Test
    public void testGenerator(){
        logger.info("雪花算法策略，生成ID：{}", idsIdGeneratorMap.get(Constants.Ids.SnowFlake).nextId());
        logger.info("日期算法策略，生成ID：{}", idsIdGeneratorMap.get(Constants.Ids.ShortCode).nextId());
        logger.info("随机算法策略，生成ID：{}", idsIdGeneratorMap.get(Constants.Ids.RandomNumberic).nextId());
    }
}

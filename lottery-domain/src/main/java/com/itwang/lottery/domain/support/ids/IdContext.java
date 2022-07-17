package com.itwang.lottery.domain.support.ids;

import com.itwang.lottery.domain.support.ids.policy.RandomNumberic;
import com.itwang.lottery.domain.support.ids.policy.ShortCode;
import com.itwang.lottery.domain.support.ids.policy.SnowFlake;
import it.comwang.lottery.common.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:17  1:29
 */
@Configuration
public class IdContext {


    @Bean
    public Map<Constants.Ids, IdGenerator> idGenerator(SnowFlake snowFlake, ShortCode shortCode, RandomNumberic randomNumberic){
        Map<Constants.Ids, IdGenerator> idsIdGeneratorMap = new HashMap<>();
        idsIdGeneratorMap.put(Constants.Ids.SnowFlake, snowFlake);
        idsIdGeneratorMap.put(Constants.Ids.ShortCode, shortCode);
        idsIdGeneratorMap.put(Constants.Ids.RandomNumberic, randomNumberic);
        return idsIdGeneratorMap;
    }
}

package com.itwang.lottery.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.itwang.lottery.domain.support.ids.IdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:17  1:37
 */
@Component
public class SnowFlake implements IdGenerator {

    private Snowflake snowFlake;


    @Override
    public synchronized long nextId() {
        return snowFlake.nextId();
    }

    @PostConstruct
    public void init(){
        long workId;
        try{
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        }catch (Exception e){
            workId = NetUtil.getLocalhostStr().hashCode();
        }

        workId = workId >> 16 & 31;
        long dataCenterId = 1L;
        snowFlake = IdUtil.createSnowflake(workId, dataCenterId);
    }
}

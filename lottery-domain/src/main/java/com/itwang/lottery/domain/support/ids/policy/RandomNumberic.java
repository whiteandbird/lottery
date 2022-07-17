package com.itwang.lottery.domain.support.ids.policy;

import com.itwang.lottery.domain.support.ids.IdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:17  1:30
 */
@Component
public class RandomNumberic implements IdGenerator {
    @Override
    public synchronized long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}

package com.itwang.lottery.domain.support.ids.policy;

import com.itwang.lottery.domain.support.ids.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Random;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:17  1:34
 */
@Component
public class ShortCode implements IdGenerator {
    @Override
    public synchronized long nextId() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        StringBuilder idStr = new StringBuilder();

        idStr.append(year-2020);
        idStr.append(hour);
        idStr.append(String.format("%02d", week));
        idStr.append(day);
        idStr.append(String.format("%03d", new Random().nextInt(1000)));
        return Long.parseLong(idStr.toString());
    }
}

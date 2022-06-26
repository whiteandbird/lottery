package com.itwang.lottery.domain.service.algorithm;

import com.itwang.lottery.domain.model.vo.AwardRateInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  11:28
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{
    private final int HASH_INCREMENT = 0x61c88647;

    private final int RATE_TUPLE_LENGTH = 128;

    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    protected Map<Long, List<AwardRateInfo>> awardRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList) {
        awardRateInfoMap.put(strategyId, awardRateInfoList);
        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        for(AwardRateInfo awardRateInfo : awardRateInfoList){
            int rateVal = awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();
            for(int i = cursorVal+1; i<=(rateVal + cursorVal); i++){
                rateTuple[hashIdx(i)] = awardRateInfo.getAwardId();
            }
            cursorVal += rateVal;
        }

    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }

    protected int hashIdx(int val){
        int hashcode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashcode & (RATE_TUPLE_LENGTH - 1);
    }

}

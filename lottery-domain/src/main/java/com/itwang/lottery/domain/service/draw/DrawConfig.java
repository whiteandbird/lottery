package com.itwang.lottery.domain.service.draw;

import com.itwang.lottery.domain.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  15:13
 */
public class DrawConfig {
    @Resource
    private IDrawAlgorithm defaultRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    protected  static Map<Integer, IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void init(){
        drawAlgorithmMap.put(1, defaultRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(2, singleRateRandomDrawAlgorithm);
    }
}

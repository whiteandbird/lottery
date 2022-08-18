package com.itwang.lottery.domain.activity.model.res;

import it.comwang.lottery.common.Result;

/**
 * 活动参与结果
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:18  22:34
 */
public class PartakeResult extends Result {

    /**
     * 参与活动对应的策略ID
     */
    private Long strategyId;

    private Long takeId;

    public PartakeResult(String code, String info){
        super(code, info);
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }
}

package com.itwang.lottery.domain.strategy.model.req;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  15:01
 */
public class DrawReq {
    private String uId;

    private Long strategyId;

    /**
     * 防重ID
     */
    private String uuId;


    public DrawReq() {
    }

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }

    public DrawReq(String uId, Long strategyId, String uuId) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.uuId = uuId;
    }


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

}

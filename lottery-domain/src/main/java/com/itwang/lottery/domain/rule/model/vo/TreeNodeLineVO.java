package com.itwang.lottery.domain.rule.model.vo;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:23  23:33
 */
public class TreeNodeLineVO {

    /**
     * 节点from
     */
    private Long nodeIdFrom;

    /**
     * 节点to
     */
    private Long nodeIdTo;

    /**
     * 节点类型
     */
    private Integer ruleLimitType;

    /**
     * 限定值
     */
    private String ruleLimitValue;

    public Long getNodeIdFrom() {
        return nodeIdFrom;
    }

    public void setNodeIdFrom(Long nodeIdFrom) {
        this.nodeIdFrom = nodeIdFrom;
    }

    public Long getNodeIdTo() {
        return nodeIdTo;
    }

    public void setNodeIdTo(Long nodeIdTo) {
        this.nodeIdTo = nodeIdTo;
    }

    public Integer getRuleLimitType() {
        return ruleLimitType;
    }

    public void setRuleLimitType(Integer ruleLimitType) {
        this.ruleLimitType = ruleLimitType;
    }

    public String getRuleLimitValue() {
        return ruleLimitValue;
    }

    public void setRuleLimitValue(String ruleLimitValue) {
        this.ruleLimitValue = ruleLimitValue;
    }
}

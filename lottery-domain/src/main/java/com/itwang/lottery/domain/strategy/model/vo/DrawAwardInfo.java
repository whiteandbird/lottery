package com.itwang.lottery.domain.strategy.model.vo;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:28  22:21
 */
public class DrawAwardInfo {

    private String awardId;

    private String awardName;

    private Integer awardType;

    /**
     * 奖品内容「描述、奖品码、sku」
     */
    private String awardContent;

    public DrawAwardInfo() {
    }

    public DrawAwardInfo(String awardId, Integer awardType, String awardName,String awardContent) {
        this.awardId = awardId;
        this.awardType = awardType;
        this.awardName = awardName;
        this.awardContent = awardContent;
    }


    public DrawAwardInfo(String rewardId, String awardName) {
        this.awardId = rewardId;
        this.awardName = awardName;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setRewardId(String rewardId) {
        this.awardId = rewardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }
}

package com.itwang.lottery.application.process.req;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:18  23:35
 */
public class DrawProcessReq {
    private String uId;

    private Long activityId;

    public DrawProcessReq() {
    }

    public DrawProcessReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

}

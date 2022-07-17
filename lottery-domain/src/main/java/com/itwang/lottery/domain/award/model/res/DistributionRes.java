package com.itwang.lottery.domain.award.model.res;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  9:46
 */
public class DistributionRes {

    private String uId;

    private Integer code;

    private String info;

    private String statementId;

    public DistributionRes() {
    }

    /**
     * 构造函数
     *
     * @param uId   用户ID
     * @param code  编码
     * @param info  描述
     */
    public DistributionRes(String uId, Integer code, String info) {
        this.uId = uId;
        this.code = code;
        this.info = info;
    }

    public DistributionRes(String uId, Integer code, String info, String statementId) {
        this.uId = uId;
        this.code = code;
        this.info = info;
        this.statementId = statementId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

}

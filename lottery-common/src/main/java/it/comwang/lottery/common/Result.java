package it.comwang.lottery.common;


import java.io.Serializable;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:25  12:08
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -3826891916021780628L;

    private String code;

    private String info;

    public static Result buildResult(Constants.ResponseCode res, String info){
        return new Result(res.getCode(), info);
    }

    public static Result buildResult(Constants.ResponseCode responseCode){
        return new Result(responseCode.getCode(), responseCode.getInfo());
    }

    public static Result buildResult(String code, String info){
        return new Result(code, info);
    }

    public static Result buildSuccessResult(){
        return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static Result buildErrorResult(){
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }

    public static Result buildErrorResult(String error){
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), error);
    }


    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public Result(String code, String info){
        this.code = code;
        this.info = info;
    }
}

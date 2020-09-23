package com.example.demo.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/1 18:12
 */
@Data
public class Result<T> implements Serializable {
    private static final String CODE_SUCCESS = "200";

    private static final String CODE_FAIL = "400";

    private static final String MSG_SUCCESS="success";

    private static final String MSG_FAIL="failed";

    private String msg;

    private String code;

    private T data;

    public Result(){

    }
    public Result(String msg){
        this.msg = msg;
    }

    public Result(String msg,String code){
        this.msg = msg;
        this.code = code;
    }
    public Result(String msg,T data){
        this.msg = msg;
        this.data = data;
    }
    public Result(String msg,String code,T data){
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
    public static Result success(){
        return new Result(Result.MSG_SUCCESS,Result.CODE_SUCCESS);
    }
    public static Result success(Object data){
        return new Result(Result.MSG_SUCCESS,Result.CODE_SUCCESS,data);
    }
    public static Result fail(){
        return new Result(Result.MSG_FAIL,Result.CODE_FAIL);
    }

}

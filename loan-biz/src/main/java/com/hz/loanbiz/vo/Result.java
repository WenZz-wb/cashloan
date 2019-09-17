package com.hz.loanbiz.vo;


import lombok.Data;

@Data
public class Result<T> {

    private int code; //0-请求成功，1-请求失败
    private String msg; //请求描述
    private T t;


    /**
     * 返回失败
     * @param code
     * @param msg
     * @return
     */
    public static Result getFail(int code ,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回成功
     * @return
     */
    public static Result getSuc(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("请求成功");
        return result;
    }

}

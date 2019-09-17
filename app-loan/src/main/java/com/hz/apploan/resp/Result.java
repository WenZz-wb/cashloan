package com.hz.apploan.resp;

import lombok.Data;

/**
 * 返回给app实体
 */
@Data
public class Result {

    private int code; //0-请求成功，1-请求失败
    private String msg; //请求描述


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


}

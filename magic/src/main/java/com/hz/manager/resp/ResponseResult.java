package com.hz.manager.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Title: ResponseResult
 * @Description: 前端请求响应结果,code:编码,message:描述,obj对象，可以是单个数据对象，数据列表或者PageInfo
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/23 9:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult implements Serializable{

    private String code;
    private String message;
    private Object obj;

    ResponseResult(String code,String message){
      this.code=code;
      this.message=message;
    }


    public static   ResponseResult success(){
          return   new ResponseResult("1","成功");
    }

    public   static  ResponseResult erro(){
        return  new ResponseResult("0","失败");
    }
}

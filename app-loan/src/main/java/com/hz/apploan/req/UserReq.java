package com.hz.apploan.req;

import lombok.Data;

@Data
public class UserReq {

    private String mobile;
    private String password;
    private String bizType; // 0-注册，1-登录，

    private String verifyCode; //短信验证码



}

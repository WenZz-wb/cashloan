package com.hz.userloan.vo;

import lombok.Data;


/**
 * 注册需要提供的信息
 */

@Data
public class UserRegReq {

    private String mobile;
    private String password;
    private String verifyCode;
    private String bizType;
}

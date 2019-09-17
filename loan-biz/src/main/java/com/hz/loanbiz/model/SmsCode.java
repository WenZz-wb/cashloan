package com.hz.loanbiz.model;

import lombok.Data;

import java.util.Date;

@Data
public class SmsCode {

    private Long id;

    private String userMobile;

    private String bizType;

    private String smsCode;

    private Date createDate;

}
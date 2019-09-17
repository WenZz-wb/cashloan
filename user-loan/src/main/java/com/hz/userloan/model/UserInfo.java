package com.hz.userloan.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {

    private Long id;

    private String userId;

    private String status;

    private String userName;

    private String userPassword;

    private String userMobile;

    private Date createDate;

    private String realName;

    private String idCard;

}
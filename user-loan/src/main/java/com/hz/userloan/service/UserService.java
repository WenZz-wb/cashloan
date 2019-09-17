package com.hz.userloan.service;


import com.hz.userloan.vo.Result;
import com.hz.userloan.vo.UserLoginReq;
import com.hz.userloan.vo.UserRegReq;

/**
 * 用户注册和登录的验证
 */

public interface UserService {
    /**
     * 用户注册
     * @param regReq
     * @return
     */
    public Result userRegister(UserRegReq regReq);

    /**
     * 用户登录
     * @param userLoginReq
     * @return
     */
    public Result userLogin(UserLoginReq userLoginReq);

}

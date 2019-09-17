package com.hz.userloan.service.impl;

import com.hz.userloan.Client.IClient;
import com.hz.userloan.mapper.UserMapper;
import com.hz.userloan.model.UserInfo;
import com.hz.userloan.service.UserService;
import com.hz.userloan.utils.JWTUtils;
import com.hz.userloan.vo.Result;
import com.hz.userloan.vo.UserData;
import com.hz.userloan.vo.UserLoginReq;
import com.hz.userloan.vo.UserRegReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    IClient iClient;

    @Autowired
    UserMapper userMapper;


    /**
     * 用户注册账号创建和验证码效验
     * @param userReg
     * @return
     */

    @Override
    public Result userRegister(UserRegReq userReg) {
        String mobile = userReg.getMobile();
        String verifyCode = userReg.getVerifyCode();

        //注册判断和验证，验证码是否正确
        Result result = iClient.getSmsCode(mobile, userReg.getBizType());
        if (ObjectUtils.isEmpty(result)) {
            return Result.getFail(-1, "查询验证码失败");
        }
        if (result.getCode() != 0) {
            return Result.getFail(-1, "查询验证码失败");
        }
        String verification = (String) result.getT();
        logger.info("手机号码是:{},对应的验证码是:{}", mobile, verification);
        if (!verifyCode.equals(verification)) {
            return Result.getFail(-1, "输入的验证码不正确");
        }
        String userId = UUID.randomUUID().toString().replace("-", "");
        UserInfo userInfo = new UserInfo();
        userInfo.setCreateDate(new Date());
        userInfo.setStatus("0");
        userInfo.setUserMobile(mobile);
        userInfo.setUserId(userId);
        userInfo.setUserPassword(userReg.getPassword());
        int ss = userMapper.insertSelective(userInfo);
        return Result.getSuc();
    }

    /**
     * 用户登录账号密码判断
     * @param userLoginReq
     * @return
     */

    @Override
    public Result userLogin(UserLoginReq userLoginReq) {
        UserInfo userInfo = userMapper.queryUserByMobile(userLoginReq.getMobile());

        if (ObjectUtils.isEmpty(userInfo)){
            return Result.getFail(-1,"手机号不存在");
        }

        if (!userLoginReq.getPassword().equals(userInfo.getUserPassword())) {
            return Result.getFail(-1,"密码输入不正确");
        }

        //登录成功，生成token
        String token = JWTUtils.sign(userLoginReq.getMobile(),userLoginReq.getPassword());
        UserData   userData = new UserData();
        userData.setToken(token);
        userData.setUserId(userInfo.getUserId());
        Result result = Result.getSuc();
        result.setT(userData);
        logger.info(result.toString());
        return result;
    }
}

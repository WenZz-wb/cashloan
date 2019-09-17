package com.hz.apploan.controller;


import com.hz.apploan.req.UserReq;
import com.hz.apploan.resp.Result;
import com.hz.apploan.service.SmsService;
import com.hz.apploan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    SmsService smsService;

    @Autowired
    UserService userService;

    /**
     * 发送短信
     *
     * @param userReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/sms")
    public Result senSms(@RequestBody UserReq userReq) {
        logger.info("接受到手机号:{}发送短信验证码请求,bizType:{}", userReq.getMobile(), userReq.getBizType());
        String bizType = userReq.getBizType();
        String mobile = userReq.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return Result.getFail(-1, "bizType不能为空");
        }
        if (StringUtils.isEmpty(bizType)) {
            return Result.getFail(-1, "mobile不能为空");
        }

        return smsService.sendSms(mobile, bizType);
    }


    /**
     * 用户注册
     *
     * @param userReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/reg")
    public Result userRegister(@RequestBody UserReq userReq) {
        logger.info("接受到手机号:{}注册请求...", userReq.getMobile());
        String verifyCode = userReq.getVerifyCode();
        String mobile = userReq.getMobile();

        if (StringUtils.isEmpty(verifyCode)) {
            return Result.getFail(-1, "短信验证不能为空");
        }

        if (StringUtils.isEmpty(mobile)) {
            return Result.getFail(-1, "手机号不能为空");
        }

        if (StringUtils.isEmpty(userReq.getPassword())) {
            return Result.getFail(-1, "密码不能为空");
        }

        if (StringUtils.isEmpty(userReq.getBizType())) {
            return Result.getFail(-1, "bizType不能为空");
        }
        return userService.userReg(userReq);
    }

    /**
     * 用户登录
     *
     * @param userReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public Result userLogin(@RequestBody UserReq userReq) {
        logger.info("接收来自手机号:{},密码:{},的登录请求", userReq.getMobile(), userReq.getPassword());
        if (StringUtils.isEmpty(userReq.getMobile())) {
            return Result.getFail(-1, "手机号不能为空");
        }

        if (StringUtils.isEmpty(userReq.getPassword())) {
            return Result.getFail(-1, "密码不能为空");
        }

        return userService.userLogin(userReq);
    }

}

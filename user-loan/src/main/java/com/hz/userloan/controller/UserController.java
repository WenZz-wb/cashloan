package com.hz.userloan.controller;

import com.hz.userloan.service.UserService;
import com.hz.userloan.vo.Result;
import com.hz.userloan.vo.UserLoginReq;
import com.hz.userloan.vo.UserRegReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * 用户注册
     *
     * @param userReg
     * @return
     */
    @ResponseBody
    @RequestMapping("/reg")
    public Result userReg(@RequestBody UserRegReq userReg) {
        logger.info("请求到信息UserRegReq:{}", userReg.toString());
        if (ObjectUtils.isEmpty(userReg)) {
            return Result.getFail(-1, "未接收到数据");
        }

        Result result = userService.userRegister(userReg);

        return result;

    }

    /**
     * 用户登录
     */
    @ResponseBody
    @RequestMapping("/login")
    public Result userLoin(@RequestBody UserLoginReq loginReq) {
        logger.info("接收到手机号:{}的登录请求,密码:{}", loginReq.getMobile(), loginReq.getPassword());

        if (ObjectUtils.isEmpty(loginReq)) {
            return Result.getFail(-1, "未接收到数据");
        }
        Result result = userService.userLogin(loginReq);

        return result;
    }
}

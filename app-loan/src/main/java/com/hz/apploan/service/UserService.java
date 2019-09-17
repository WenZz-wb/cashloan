package com.hz.apploan.service;


import com.hz.apploan.req.UserReq;
import com.hz.apploan.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient(value = "user-loan" )
public interface UserService {
    @ResponseBody
    @RequestMapping(value = "/user/reg")
    public Result userReg(@RequestBody UserReq userReq);

    @ResponseBody
    @RequestMapping(value = "/user/login")
    public Result userLogin(@RequestBody UserReq userReq);
}

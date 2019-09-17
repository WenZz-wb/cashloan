package com.hz.userloan.Client;

import com.hz.userloan.Client.impl.ClientImpl;
import com.hz.userloan.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "loan-biz", fallback = ClientImpl.class)
public interface IClient {
    /**
     * 查询验证码
     *
     * @param mobile
     * @param bizType
     * @return
     */
    @RequestMapping(value = "/sms/query")
    public Result getSmsCode(@RequestParam("mobile") String mobile, @RequestParam("bizType") String bizType);

}

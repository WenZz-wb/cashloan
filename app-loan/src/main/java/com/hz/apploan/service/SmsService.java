package com.hz.apploan.service;

import com.hz.apploan.resp.Result;
import com.hz.apploan.service.impl.SmsServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "loan-biz",fallback = SmsServiceImpl.class )
public interface SmsService {

    /**
     * 发送短信
     * @param mobile
     * @param bizType
     */
    @ResponseBody
    @RequestMapping(value = "/sms/send")
    public Result sendSms(@RequestParam("mobile") String mobile, @RequestParam("bizType") String bizType);
}

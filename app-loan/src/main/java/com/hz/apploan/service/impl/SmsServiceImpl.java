package com.hz.apploan.service.impl;

import com.hz.apploan.resp.Result;
import com.hz.apploan.service.SmsService;

public class SmsServiceImpl implements SmsService {

    @Override
    public Result sendSms(String mobile, String bizType) {
        return Result.getFail(-1,"发送短信失败");
    }
}

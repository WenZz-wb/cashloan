package com.hz.loanbiz.service.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.hz.loanbiz.mapper.SmsCodeMapper;
import com.hz.loanbiz.model.SmsCode;
import com.hz.loanbiz.service.SendSmsService;
import com.hz.loanbiz.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Date;

@Service
public class SendSmsServiceImpl implements SendSmsService {

    private static final Logger logger = LoggerFactory.getLogger(SendSmsServiceImpl.class);

    @Autowired
    SmsCodeMapper smsCodeMapper;

    @Override
    public Result send(String mobile, String bizType) {
        logger.info("开始处理手机号：{}发送短信请求", mobile);
        //生成验证码
        int str = (int) ((Math.random() * 9 + 1) * 100000);

        SmsSingleSender ssender = new SmsSingleSender(1400009099, "9ff91d87c2cd7cd0ea762f141975d1df37481d48700d70ac37470aefc60f9bad");
        try {

            SmsSingleSenderResult smsRlt = ssender.send(0, "86", mobile,
                    "【腾讯云】您的验证码是: 5678", "", "");

            logger.info("短信发送结果：{}",smsRlt.errMsg);
            int smsCode = smsRlt.result;
            SmsCode record = new SmsCode();
            record.setBizType(bizType);
            record.setCreateDate(new Date());
            record.setSmsCode(String.valueOf(str));
            record.setUserMobile(mobile);
            smsCodeMapper.insertService(record);
            return Result.getSuc();

        } catch (HTTPException e) {
            logger.error("HTTPException",e);
            return Result.getFail(-1,"发送短信失败");
        } catch (IOException e) {
            logger.error("IOException",e);
            return Result.getFail(-1,"发送短信失败");
        }

    }

    @Override
    public Result query(String mobile, String bizType) {
        logger.info("收到查询手机号的信息:{},验证码:{}",mobile,bizType);

        SmsCode smsCode = smsCodeMapper.querySmsCodeByMobile(mobile,bizType);
        Result result = new Result();

        if(ObjectUtils.isEmpty(smsCode)){
            return Result.getFail(-1,"没有数据");
        }
        result = Result.getSuc();
        result.setT(smsCode.getSmsCode());
        logger.info(result.toString());
        return result;
    }
}

package com.hz.loanbiz.service;

import com.hz.loanbiz.vo.Result;

public interface SendSmsService {

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @param bizType
     * @return
     */
    public Result send(String mobile, String bizType);


    /**
     * 查询短信验证码
     *
     * @param mobile
     * @param bizType
     * @return
     */
    public Result query(String mobile, String bizType);


}

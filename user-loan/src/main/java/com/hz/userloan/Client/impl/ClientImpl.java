package com.hz.userloan.Client.impl;

import com.hz.userloan.Client.IClient;
import com.hz.userloan.vo.Result;

public class ClientImpl implements IClient {
    @Override
    public Result getSmsCode(String mobile, String bizType) {
        return Result.getFail(-1,"查询短信失败");
    }
}

package com.hz.loanbiz.mapper;

import com.hz.loanbiz.model.SmsCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsCodeMapper {

    int insertService(SmsCode record);

    SmsCode selectByPrimaryKey(Long id);

    /**
     * 根据手机号和业务类型查询验证码
     * @param mobile
     * @param bizType
     * @return
     */
    SmsCode querySmsCodeByMobile(@Param("mobile")String mobile , @Param("bizType")String bizType);


}

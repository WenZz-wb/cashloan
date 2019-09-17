package com.hz.userloan.mapper;

import com.hz.userloan.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    /**
     * 根据手机查询
     * @param mobile
     * @return
     */
    UserInfo queryUserByMobile(@Param("mobile")String mobile);

}

package com.tongzhu.user.mapper.ext;


import com.tongzhu.user.model.UserMailSingle;
import com.tongzhu.user.service.vo.UserMailSingleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMailSingleExtMapper {

    List<UserMailSingleVO> selectUserMailSingleVOList(@Param("userId") String userId,@Param("status") Integer status,@Param("receive") Integer receive);

    void batchInsert(List<UserMailSingle> list);

    Integer updateMailAll(@Param("userId") String userId,@Param("receive")  Integer receive,@Param("read") Integer read);

    Integer updateMailSingle(@Param("userId") String userId,@Param("receive")  Integer receive,@Param("read") Integer read);

    int userMailMessage(@Param("userId") String userId,@Param("status") Integer status,@Param("receive") Integer receive);
}
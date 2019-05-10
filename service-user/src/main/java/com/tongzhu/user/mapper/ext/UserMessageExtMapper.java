package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.controller.vo.UserMessageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMessageExtMapper {

    List<UserMessageVO> findReceivedMessageByPage(@Param("userId") String userId,@Param("status") int status,@Param("list") List<Integer> list);

    List<UserMessageVO> findReceivedMessageByType(@Param("userId") String userId,@Param("type") int type);

}
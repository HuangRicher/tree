package com.tongzhu.friend.mapper.ext;


import org.apache.ibatis.annotations.Param;

public interface DailyTaskInfoExtMapper {
    Integer getUserLiveness(@Param("userId") String userId, @Param("receiveAward") int receiveAward);
}
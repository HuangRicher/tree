package com.tongzhu.friend.mapper.ext;

import com.tongzhu.friend.service.vo.DailyTaskVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyTaskRecordExtMapper {

    List<DailyTaskVO> getDailyTaskVOList(@Param("userId") String userId);
}
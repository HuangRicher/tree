package com.tongzhu.friend.mapper.ext;

import com.tongzhu.friend.model.TaskActivityRecord;
import com.tongzhu.friend.service.vo.TaskActivityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskActivityRecordExtMapper {
    List<TaskActivityVO> getTaskActivityRecordList(@Param("userId") String userId,@Param("liveness") Integer  liveness);

    List<TaskActivityRecord> getTaskActivityRecordListByLiveness(@Param("userId") String userId,@Param("liveness") Integer  liveness);
}
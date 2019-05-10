package com.tongzhu.friend.mapper.ext;

import com.tongzhu.friend.model.TaskBranchRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskBranchRecordExtMapper {
    List<TaskBranchRecord> getTaskBranchRecordUndoneList(@Param("userId") String userId,@Param("taskReceive") int taskReceive);
}
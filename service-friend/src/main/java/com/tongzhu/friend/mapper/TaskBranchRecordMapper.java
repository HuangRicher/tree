package com.tongzhu.friend.mapper;

import com.tongzhu.friend.model.TaskBranchRecord;
import com.tongzhu.friend.model.TaskBranchRecordExample;
import com.tongzhu.friend.model.TaskBranchRecordKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskBranchRecordMapper {
    int countByExample(TaskBranchRecordExample example);

    int deleteByExample(TaskBranchRecordExample example);

    int deleteByPrimaryKey(TaskBranchRecordKey key);

    int insert(TaskBranchRecord record);

    int insertSelective(TaskBranchRecord record);

    List<TaskBranchRecord> selectByExample(TaskBranchRecordExample example);

    TaskBranchRecord selectByPrimaryKey(TaskBranchRecordKey key);

    int updateByExampleSelective(@Param("record") TaskBranchRecord record, @Param("example") TaskBranchRecordExample example);

    int updateByExample(@Param("record") TaskBranchRecord record, @Param("example") TaskBranchRecordExample example);

    int updateByPrimaryKeySelective(TaskBranchRecord record);

    int updateByPrimaryKey(TaskBranchRecord record);
}
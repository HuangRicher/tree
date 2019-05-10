package com.tongzhu.friend.mapper;

import com.tongzhu.friend.model.TaskActivityRecord;
import com.tongzhu.friend.model.TaskActivityRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskActivityRecordMapper {
    int countByExample(TaskActivityRecordExample example);

    int deleteByExample(TaskActivityRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TaskActivityRecord record);

    int insertSelective(TaskActivityRecord record);

    List<TaskActivityRecord> selectByExample(TaskActivityRecordExample example);

    TaskActivityRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TaskActivityRecord record, @Param("example") TaskActivityRecordExample example);

    int updateByExample(@Param("record") TaskActivityRecord record, @Param("example") TaskActivityRecordExample example);

    int updateByPrimaryKeySelective(TaskActivityRecord record);

    int updateByPrimaryKey(TaskActivityRecord record);
}
package com.tongzhu.friend.mapper;

import com.tongzhu.friend.model.DailyTaskRecord;
import com.tongzhu.friend.model.DailyTaskRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyTaskRecordMapper {
    int countByExample(DailyTaskRecordExample example);

    int deleteByExample(DailyTaskRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(DailyTaskRecord record);

    int insertSelective(DailyTaskRecord record);

    List<DailyTaskRecord> selectByExample(DailyTaskRecordExample example);

    DailyTaskRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DailyTaskRecord record, @Param("example") DailyTaskRecordExample example);

    int updateByExample(@Param("record") DailyTaskRecord record, @Param("example") DailyTaskRecordExample example);

    int updateByPrimaryKeySelective(DailyTaskRecord record);

    int updateByPrimaryKey(DailyTaskRecord record);
}
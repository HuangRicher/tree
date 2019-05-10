package com.tongzhu.friend.mapper;

import com.tongzhu.friend.model.DailyTaskInfo;
import com.tongzhu.friend.model.DailyTaskInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyTaskInfoMapper {
    int countByExample(DailyTaskInfoExample example);

    int deleteByExample(DailyTaskInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DailyTaskInfo record);

    int insertSelective(DailyTaskInfo record);

    List<DailyTaskInfo> selectByExample(DailyTaskInfoExample example);

    DailyTaskInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DailyTaskInfo record, @Param("example") DailyTaskInfoExample example);

    int updateByExample(@Param("record") DailyTaskInfo record, @Param("example") DailyTaskInfoExample example);

    int updateByPrimaryKeySelective(DailyTaskInfo record);

    int updateByPrimaryKey(DailyTaskInfo record);
}
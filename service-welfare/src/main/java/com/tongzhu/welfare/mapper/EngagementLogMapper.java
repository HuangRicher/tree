package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.EngagementLog;
import com.tongzhu.welfare.model.EngagementLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EngagementLogMapper {
    int countByExample(EngagementLogExample example);

    int deleteByExample(EngagementLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(EngagementLog record);

    int insertSelective(EngagementLog record);

    List<EngagementLog> selectByExample(EngagementLogExample example);

    EngagementLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EngagementLog record, @Param("example") EngagementLogExample example);

    int updateByExample(@Param("record") EngagementLog record, @Param("example") EngagementLogExample example);

    int updateByPrimaryKeySelective(EngagementLog record);

    int updateByPrimaryKey(EngagementLog record);
}
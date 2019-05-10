package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.EngagementCancleLog;
import com.tongzhu.welfare.model.EngagementCancleLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EngagementCancleLogMapper {
    int countByExample(EngagementCancleLogExample example);

    int deleteByExample(EngagementCancleLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(EngagementCancleLog record);

    int insertSelective(EngagementCancleLog record);

    List<EngagementCancleLog> selectByExample(EngagementCancleLogExample example);

    EngagementCancleLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EngagementCancleLog record, @Param("example") EngagementCancleLogExample example);

    int updateByExample(@Param("record") EngagementCancleLog record, @Param("example") EngagementCancleLogExample example);

    int updateByPrimaryKeySelective(EngagementCancleLog record);

    int updateByPrimaryKey(EngagementCancleLog record);
}
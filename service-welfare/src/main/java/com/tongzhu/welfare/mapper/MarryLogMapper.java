package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.MarryLog;
import com.tongzhu.welfare.model.MarryLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarryLogMapper {
    int countByExample(MarryLogExample example);

    int deleteByExample(MarryLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(MarryLog record);

    int insertSelective(MarryLog record);

    List<MarryLog> selectByExample(MarryLogExample example);

    MarryLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MarryLog record, @Param("example") MarryLogExample example);

    int updateByExample(@Param("record") MarryLog record, @Param("example") MarryLogExample example);

    int updateByPrimaryKeySelective(MarryLog record);

    int updateByPrimaryKey(MarryLog record);
}
package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.WeddingLog;
import com.tongzhu.welfare.model.WeddingLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeddingLogMapper {
    int countByExample(WeddingLogExample example);

    int deleteByExample(WeddingLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(WeddingLog record);

    int insertSelective(WeddingLog record);

    List<WeddingLog> selectByExample(WeddingLogExample example);

    WeddingLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WeddingLog record, @Param("example") WeddingLogExample example);

    int updateByExample(@Param("record") WeddingLog record, @Param("example") WeddingLogExample example);

    int updateByPrimaryKeySelective(WeddingLog record);

    int updateByPrimaryKey(WeddingLog record);
}
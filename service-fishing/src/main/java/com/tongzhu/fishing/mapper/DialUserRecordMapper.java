package com.tongzhu.fishing.mapper;

import com.tongzhu.fishing.model.DialUserRecord;
import com.tongzhu.fishing.model.DialUserRecordExample;
import com.tongzhu.fishing.model.DialUserRecordKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DialUserRecordMapper {
    int countByExample(DialUserRecordExample example);

    int deleteByExample(DialUserRecordExample example);

    int deleteByPrimaryKey(DialUserRecordKey key);

    int insert(DialUserRecord record);

    int insertSelective(DialUserRecord record);

    List<DialUserRecord> selectByExample(DialUserRecordExample example);

    DialUserRecord selectByPrimaryKey(DialUserRecordKey key);

    int updateByExampleSelective(@Param("record") DialUserRecord record, @Param("example") DialUserRecordExample example);

    int updateByExample(@Param("record") DialUserRecord record, @Param("example") DialUserRecordExample example);

    int updateByPrimaryKeySelective(DialUserRecord record);

    int updateByPrimaryKey(DialUserRecord record);
}
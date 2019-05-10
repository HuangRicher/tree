package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseCultureRecord;
import com.tongzhu.treehouse.model.TreeHouseCultureRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeHouseCultureRecordMapper {
    int countByExample(TreeHouseCultureRecordExample example);

    int deleteByExample(TreeHouseCultureRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TreeHouseCultureRecord record);

    int insertSelective(TreeHouseCultureRecord record);

    List<TreeHouseCultureRecord> selectByExample(TreeHouseCultureRecordExample example);

    TreeHouseCultureRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TreeHouseCultureRecord record, @Param("example") TreeHouseCultureRecordExample example);

    int updateByExample(@Param("record") TreeHouseCultureRecord record, @Param("example") TreeHouseCultureRecordExample example);

    int updateByPrimaryKeySelective(TreeHouseCultureRecord record);

    int updateByPrimaryKey(TreeHouseCultureRecord record);
}
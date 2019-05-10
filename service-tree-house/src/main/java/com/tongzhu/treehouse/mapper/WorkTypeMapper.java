package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.WorkType;
import com.tongzhu.treehouse.model.WorkTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkTypeMapper {
    int countByExample(WorkTypeExample example);

    int deleteByExample(WorkTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkType record);

    int insertSelective(WorkType record);

    List<WorkType> selectByExample(WorkTypeExample example);

    WorkType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkType record, @Param("example") WorkTypeExample example);

    int updateByExample(@Param("record") WorkType record, @Param("example") WorkTypeExample example);

    int updateByPrimaryKeySelective(WorkType record);

    int updateByPrimaryKey(WorkType record);
}
package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.LoveTree;
import com.tongzhu.welfare.model.LoveTreeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoveTreeMapper {
    int countByExample(LoveTreeExample example);

    int deleteByExample(LoveTreeExample example);

    int deleteByPrimaryKey(String id);

    int insert(LoveTree record);

    int insertSelective(LoveTree record);

    List<LoveTree> selectByExample(LoveTreeExample example);

    LoveTree selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LoveTree record, @Param("example") LoveTreeExample example);

    int updateByExample(@Param("record") LoveTree record, @Param("example") LoveTreeExample example);

    int updateByPrimaryKeySelective(LoveTree record);

    int updateByPrimaryKey(LoveTree record);
}
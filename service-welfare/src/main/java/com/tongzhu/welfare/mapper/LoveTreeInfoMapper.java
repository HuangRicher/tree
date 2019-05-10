package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.LoveTreeInfo;
import com.tongzhu.welfare.model.LoveTreeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoveTreeInfoMapper {
    int countByExample(LoveTreeInfoExample example);

    int deleteByExample(LoveTreeInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(LoveTreeInfo record);

    int insertSelective(LoveTreeInfo record);

    List<LoveTreeInfo> selectByExample(LoveTreeInfoExample example);

    LoveTreeInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LoveTreeInfo record, @Param("example") LoveTreeInfoExample example);

    int updateByExample(@Param("record") LoveTreeInfo record, @Param("example") LoveTreeInfoExample example);

    int updateByPrimaryKeySelective(LoveTreeInfo record);

    int updateByPrimaryKey(LoveTreeInfo record);
}
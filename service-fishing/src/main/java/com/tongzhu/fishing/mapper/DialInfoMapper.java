package com.tongzhu.fishing.mapper;

import com.tongzhu.fishing.model.DialInfo;
import com.tongzhu.fishing.model.DialInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DialInfoMapper {
    int countByExample(DialInfoExample example);

    int deleteByExample(DialInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DialInfo record);

    int insertSelective(DialInfo record);

    List<DialInfo> selectByExample(DialInfoExample example);

    DialInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DialInfo record, @Param("example") DialInfoExample example);

    int updateByExample(@Param("record") DialInfo record, @Param("example") DialInfoExample example);

    int updateByPrimaryKeySelective(DialInfo record);

    int updateByPrimaryKey(DialInfo record);
}
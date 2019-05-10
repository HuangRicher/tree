package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.GemInfo;
import com.tongzhu.usergoods.model.GemInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GemInfoMapper {
    int countByExample(GemInfoExample example);

    int deleteByExample(GemInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(GemInfo record);

    int insertSelective(GemInfo record);

    List<GemInfo> selectByExample(GemInfoExample example);

    GemInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GemInfo record, @Param("example") GemInfoExample example);

    int updateByExample(@Param("record") GemInfo record, @Param("example") GemInfoExample example);

    int updateByPrimaryKeySelective(GemInfo record);

    int updateByPrimaryKey(GemInfo record);
}
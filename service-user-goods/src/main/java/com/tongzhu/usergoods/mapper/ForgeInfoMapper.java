package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.ForgeInfo;
import com.tongzhu.usergoods.model.ForgeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForgeInfoMapper {
    int countByExample(ForgeInfoExample example);

    int deleteByExample(ForgeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ForgeInfo record);

    int insertSelective(ForgeInfo record);

    List<ForgeInfo> selectByExample(ForgeInfoExample example);

    ForgeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ForgeInfo record, @Param("example") ForgeInfoExample example);

    int updateByExample(@Param("record") ForgeInfo record, @Param("example") ForgeInfoExample example);

    int updateByPrimaryKeySelective(ForgeInfo record);

    int updateByPrimaryKey(ForgeInfo record);
}
package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.PetLevelInfo;
import com.tongzhu.usergoods.model.PetLevelInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PetLevelInfoMapper {
    int countByExample(PetLevelInfoExample example);

    int deleteByExample(PetLevelInfoExample example);

    int deleteByPrimaryKey(Integer level);

    int insert(PetLevelInfo record);

    int insertSelective(PetLevelInfo record);

    List<PetLevelInfo> selectByExample(PetLevelInfoExample example);

    PetLevelInfo selectByPrimaryKey(Integer level);

    int updateByExampleSelective(@Param("record") PetLevelInfo record, @Param("example") PetLevelInfoExample example);

    int updateByExample(@Param("record") PetLevelInfo record, @Param("example") PetLevelInfoExample example);

    int updateByPrimaryKeySelective(PetLevelInfo record);

    int updateByPrimaryKey(PetLevelInfo record);
}
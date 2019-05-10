package com.tongzhu.usergoods.mapper;

import com.tongzhu.usergoods.model.PetInfo;
import com.tongzhu.usergoods.model.PetInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PetInfoMapper {
    int countByExample(PetInfoExample example);

    int deleteByExample(PetInfoExample example);

    int deleteByPrimaryKey(String petId);

    int insert(PetInfo record);

    int insertSelective(PetInfo record);

    List<PetInfo> selectByExample(PetInfoExample example);

    PetInfo selectByPrimaryKey(String petId);

    int updateByExampleSelective(@Param("record") PetInfo record, @Param("example") PetInfoExample example);

    int updateByExample(@Param("record") PetInfo record, @Param("example") PetInfoExample example);

    int updateByPrimaryKeySelective(PetInfo record);

    int updateByPrimaryKey(PetInfo record);
}
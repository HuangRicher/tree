package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingPetshopSetting;
import com.tongzhu.welfare.model.BuildingPetshopSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingPetshopSettingMapper {
    int countByExample(BuildingPetshopSettingExample example);

    int deleteByExample(BuildingPetshopSettingExample example);

    int deleteByPrimaryKey(Integer grade);

    int insert(BuildingPetshopSetting record);

    int insertSelective(BuildingPetshopSetting record);

    List<BuildingPetshopSetting> selectByExample(BuildingPetshopSettingExample example);

    BuildingPetshopSetting selectByPrimaryKey(Integer grade);

    int updateByExampleSelective(@Param("record") BuildingPetshopSetting record, @Param("example") BuildingPetshopSettingExample example);

    int updateByExample(@Param("record") BuildingPetshopSetting record, @Param("example") BuildingPetshopSettingExample example);

    int updateByPrimaryKeySelective(BuildingPetshopSetting record);

    int updateByPrimaryKey(BuildingPetshopSetting record);
}
package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingChurchSetting;
import com.tongzhu.welfare.model.BuildingChurchSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingChurchSettingMapper {
    int countByExample(BuildingChurchSettingExample example);

    int deleteByExample(BuildingChurchSettingExample example);

    int deleteByPrimaryKey(Integer grade);

    int insert(BuildingChurchSetting record);

    int insertSelective(BuildingChurchSetting record);

    List<BuildingChurchSetting> selectByExample(BuildingChurchSettingExample example);

    BuildingChurchSetting selectByPrimaryKey(Integer grade);

    int updateByExampleSelective(@Param("record") BuildingChurchSetting record, @Param("example") BuildingChurchSettingExample example);

    int updateByExample(@Param("record") BuildingChurchSetting record, @Param("example") BuildingChurchSettingExample example);

    int updateByPrimaryKeySelective(BuildingChurchSetting record);

    int updateByPrimaryKey(BuildingChurchSetting record);
}
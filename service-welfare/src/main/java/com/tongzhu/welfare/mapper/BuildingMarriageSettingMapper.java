package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingMarriageSetting;
import com.tongzhu.welfare.model.BuildingMarriageSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingMarriageSettingMapper {
    int countByExample(BuildingMarriageSettingExample example);

    int deleteByExample(BuildingMarriageSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BuildingMarriageSetting record);

    int insertSelective(BuildingMarriageSetting record);

    List<BuildingMarriageSetting> selectByExample(BuildingMarriageSettingExample example);

    BuildingMarriageSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BuildingMarriageSetting record, @Param("example") BuildingMarriageSettingExample example);

    int updateByExample(@Param("record") BuildingMarriageSetting record, @Param("example") BuildingMarriageSettingExample example);

    int updateByPrimaryKeySelective(BuildingMarriageSetting record);

    int updateByPrimaryKey(BuildingMarriageSetting record);
}
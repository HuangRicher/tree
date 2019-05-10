package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingWineshopSetting;
import com.tongzhu.welfare.model.BuildingWineshopSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingWineshopSettingMapper {
    int countByExample(BuildingWineshopSettingExample example);

    int deleteByExample(BuildingWineshopSettingExample example);

    int deleteByPrimaryKey(Integer grade);

    int insert(BuildingWineshopSetting record);

    int insertSelective(BuildingWineshopSetting record);

    List<BuildingWineshopSetting> selectByExample(BuildingWineshopSettingExample example);

    BuildingWineshopSetting selectByPrimaryKey(Integer grade);

    int updateByExampleSelective(@Param("record") BuildingWineshopSetting record, @Param("example") BuildingWineshopSettingExample example);

    int updateByExample(@Param("record") BuildingWineshopSetting record, @Param("example") BuildingWineshopSettingExample example);

    int updateByPrimaryKeySelective(BuildingWineshopSetting record);

    int updateByPrimaryKey(BuildingWineshopSetting record);
}
package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingSmithySetting;
import com.tongzhu.welfare.model.BuildingSmithySettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingSmithySettingMapper {
    int countByExample(BuildingSmithySettingExample example);

    int deleteByExample(BuildingSmithySettingExample example);

    int deleteByPrimaryKey(Integer grade);

    int insert(BuildingSmithySetting record);

    int insertSelective(BuildingSmithySetting record);

    List<BuildingSmithySetting> selectByExample(BuildingSmithySettingExample example);

    BuildingSmithySetting selectByPrimaryKey(Integer grade);

    int updateByExampleSelective(@Param("record") BuildingSmithySetting record, @Param("example") BuildingSmithySettingExample example);

    int updateByExample(@Param("record") BuildingSmithySetting record, @Param("example") BuildingSmithySettingExample example);

    int updateByPrimaryKeySelective(BuildingSmithySetting record);

    int updateByPrimaryKey(BuildingSmithySetting record);
}
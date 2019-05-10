package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingStatueSetting;
import com.tongzhu.welfare.model.BuildingStatueSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingStatueSettingMapper {
    int countByExample(BuildingStatueSettingExample example);

    int deleteByExample(BuildingStatueSettingExample example);

    int deleteByPrimaryKey(Integer grade);

    int insert(BuildingStatueSetting record);

    int insertSelective(BuildingStatueSetting record);

    List<BuildingStatueSetting> selectByExample(BuildingStatueSettingExample example);

    BuildingStatueSetting selectByPrimaryKey(Integer grade);

    int updateByExampleSelective(@Param("record") BuildingStatueSetting record, @Param("example") BuildingStatueSettingExample example);

    int updateByExample(@Param("record") BuildingStatueSetting record, @Param("example") BuildingStatueSettingExample example);

    int updateByPrimaryKeySelective(BuildingStatueSetting record);

    int updateByPrimaryKey(BuildingStatueSetting record);
}
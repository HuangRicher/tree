package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.WorkPositionSetting;
import com.tongzhu.treehouse.model.WorkPositionSettingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkPositionSettingMapper {
    int countByExample(WorkPositionSettingExample example);

    int deleteByExample(WorkPositionSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkPositionSetting record);

    int insertSelective(WorkPositionSetting record);

    List<WorkPositionSetting> selectByExample(WorkPositionSettingExample example);

    WorkPositionSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkPositionSetting record, @Param("example") WorkPositionSettingExample example);

    int updateByExample(@Param("record") WorkPositionSetting record, @Param("example") WorkPositionSettingExample example);

    int updateByPrimaryKeySelective(WorkPositionSetting record);

    int updateByPrimaryKey(WorkPositionSetting record);
}
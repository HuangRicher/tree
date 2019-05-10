package com.tongzhu.user.mapper;

import com.tongzhu.user.model.ExplorationSetting;
import com.tongzhu.user.model.ExplorationSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExplorationSettingMapper {
    int countByExample(ExplorationSettingExample example);

    int deleteByExample(ExplorationSettingExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExplorationSetting record);

    int insertSelective(ExplorationSetting record);

    List<ExplorationSetting> selectByExample(ExplorationSettingExample example);

    ExplorationSetting selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExplorationSetting record, @Param("example") ExplorationSettingExample example);

    int updateByExample(@Param("record") ExplorationSetting record, @Param("example") ExplorationSettingExample example);

    int updateByPrimaryKeySelective(ExplorationSetting record);

    int updateByPrimaryKey(ExplorationSetting record);
}
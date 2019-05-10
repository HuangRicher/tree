package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.FlowerSetting;
import com.tongzhu.treehouse.model.FlowerSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FlowerSettingMapper {
    int countByExample(FlowerSettingExample example);

    int deleteByExample(FlowerSettingExample example);

    int deleteByPrimaryKey(String id);

    int insert(FlowerSetting record);

    int insertSelective(FlowerSetting record);

    List<FlowerSetting> selectByExample(FlowerSettingExample example);

    FlowerSetting selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FlowerSetting record, @Param("example") FlowerSettingExample example);

    int updateByExample(@Param("record") FlowerSetting record, @Param("example") FlowerSettingExample example);

    int updateByPrimaryKeySelective(FlowerSetting record);

    int updateByPrimaryKey(FlowerSetting record);
}
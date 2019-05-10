package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.FlowerNumberSetting;
import com.tongzhu.treehouse.model.FlowerNumberSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FlowerNumberSettingMapper {
    int countByExample(FlowerNumberSettingExample example);

    int deleteByExample(FlowerNumberSettingExample example);

    int deleteByPrimaryKey(Integer dayPlantCount);

    int insert(FlowerNumberSetting record);

    int insertSelective(FlowerNumberSetting record);

    List<FlowerNumberSetting> selectByExample(FlowerNumberSettingExample example);

    FlowerNumberSetting selectByPrimaryKey(Integer dayPlantCount);

    int updateByExampleSelective(@Param("record") FlowerNumberSetting record, @Param("example") FlowerNumberSettingExample example);

    int updateByExample(@Param("record") FlowerNumberSetting record, @Param("example") FlowerNumberSettingExample example);

    int updateByPrimaryKeySelective(FlowerNumberSetting record);

    int updateByPrimaryKey(FlowerNumberSetting record);
}
package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.WelfareDaySetting;
import com.tongzhu.welfare.model.WelfareDaySettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WelfareDaySettingMapper {
    int countByExample(WelfareDaySettingExample example);

    int deleteByExample(WelfareDaySettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WelfareDaySetting record);

    int insertSelective(WelfareDaySetting record);

    List<WelfareDaySetting> selectByExample(WelfareDaySettingExample example);

    WelfareDaySetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WelfareDaySetting record, @Param("example") WelfareDaySettingExample example);

    int updateByExample(@Param("record") WelfareDaySetting record, @Param("example") WelfareDaySettingExample example);

    int updateByPrimaryKeySelective(WelfareDaySetting record);

    int updateByPrimaryKey(WelfareDaySetting record);
}
package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.WelfareMonthSetting;
import com.tongzhu.welfare.model.WelfareMonthSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WelfareMonthSettingMapper {
    int countByExample(WelfareMonthSettingExample example);

    int deleteByExample(WelfareMonthSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WelfareMonthSetting record);

    int insertSelective(WelfareMonthSetting record);

    List<WelfareMonthSetting> selectByExample(WelfareMonthSettingExample example);

    WelfareMonthSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WelfareMonthSetting record, @Param("example") WelfareMonthSettingExample example);

    int updateByExample(@Param("record") WelfareMonthSetting record, @Param("example") WelfareMonthSettingExample example);

    int updateByPrimaryKeySelective(WelfareMonthSetting record);

    int updateByPrimaryKey(WelfareMonthSetting record);
}
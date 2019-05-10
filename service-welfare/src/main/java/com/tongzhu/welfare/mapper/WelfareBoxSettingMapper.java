package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.WelfareBoxSetting;
import com.tongzhu.welfare.model.WelfareBoxSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WelfareBoxSettingMapper {
    int countByExample(WelfareBoxSettingExample example);

    int deleteByExample(WelfareBoxSettingExample example);

    int insert(WelfareBoxSetting record);

    int insertSelective(WelfareBoxSetting record);

    List<WelfareBoxSetting> selectByExample(WelfareBoxSettingExample example);

    int updateByExampleSelective(@Param("record") WelfareBoxSetting record, @Param("example") WelfareBoxSettingExample example);

    int updateByExample(@Param("record") WelfareBoxSetting record, @Param("example") WelfareBoxSettingExample example);
}
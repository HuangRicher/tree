package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.LoveClassSetting;
import com.tongzhu.welfare.model.LoveClassSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoveClassSettingMapper {
    int countByExample(LoveClassSettingExample example);

    int deleteByExample(LoveClassSettingExample example);

    int insert(LoveClassSetting record);

    int insertSelective(LoveClassSetting record);

    List<LoveClassSetting> selectByExample(LoveClassSettingExample example);

    int updateByExampleSelective(@Param("record") LoveClassSetting record, @Param("example") LoveClassSettingExample example);

    int updateByExample(@Param("record") LoveClassSetting record, @Param("example") LoveClassSettingExample example);
}
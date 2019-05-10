package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.LoveSetting;
import com.tongzhu.welfare.model.LoveSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoveSettingMapper {
    int countByExample(LoveSettingExample example);

    int deleteByExample(LoveSettingExample example);

    int insert(LoveSetting record);

    int insertSelective(LoveSetting record);

    List<LoveSetting> selectByExample(LoveSettingExample example);

    int updateByExampleSelective(@Param("record") LoveSetting record, @Param("example") LoveSettingExample example);

    int updateByExample(@Param("record") LoveSetting record, @Param("example") LoveSettingExample example);
}
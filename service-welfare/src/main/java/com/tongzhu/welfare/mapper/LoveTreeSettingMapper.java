package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.LoveTreeSetting;
import com.tongzhu.welfare.model.LoveTreeSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoveTreeSettingMapper {
    int countByExample(LoveTreeSettingExample example);

    int deleteByExample(LoveTreeSettingExample example);

    int insert(LoveTreeSetting record);

    int insertSelective(LoveTreeSetting record);

    List<LoveTreeSetting> selectByExample(LoveTreeSettingExample example);

    int updateByExampleSelective(@Param("record") LoveTreeSetting record, @Param("example") LoveTreeSettingExample example);

    int updateByExample(@Param("record") LoveTreeSetting record, @Param("example") LoveTreeSettingExample example);
}
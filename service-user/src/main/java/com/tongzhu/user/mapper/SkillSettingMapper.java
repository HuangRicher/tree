package com.tongzhu.user.mapper;

import com.tongzhu.user.model.SkillSetting;
import com.tongzhu.user.model.SkillSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkillSettingMapper {
    int countByExample(SkillSettingExample example);

    int deleteByExample(SkillSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SkillSetting record);

    int insertSelective(SkillSetting record);

    List<SkillSetting> selectByExample(SkillSettingExample example);

    SkillSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SkillSetting record, @Param("example") SkillSettingExample example);

    int updateByExample(@Param("record") SkillSetting record, @Param("example") SkillSettingExample example);

    int updateByPrimaryKeySelective(SkillSetting record);

    int updateByPrimaryKey(SkillSetting record);
}
package com.tongzhu.user.mapper.ext;

import com.tongzhu.user.model.SkillSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkillSettingExtMapper {

    List<SkillSetting> selectUserSkillSettings(@Param("userId")String userId);

    List<SkillSetting> selectSkillSettingBySkillIdAndLevel(List<SkillSetting> list);

    void batchInsert(List<SkillSetting> list);
}

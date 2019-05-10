package com.tongzhu.user.mapper;

import com.tongzhu.user.model.RoleLevelSetting;
import com.tongzhu.user.model.RoleLevelSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleLevelSettingMapper {
    int countByExample(RoleLevelSettingExample example);

    int deleteByExample(RoleLevelSettingExample example);

    int deleteByPrimaryKey(Integer level);

    int insert(RoleLevelSetting record);

    int insertSelective(RoleLevelSetting record);

    List<RoleLevelSetting> selectByExample(RoleLevelSettingExample example);

    RoleLevelSetting selectByPrimaryKey(Integer level);

    int updateByExampleSelective(@Param("record") RoleLevelSetting record, @Param("example") RoleLevelSettingExample example);

    int updateByExample(@Param("record") RoleLevelSetting record, @Param("example") RoleLevelSettingExample example);

    int updateByPrimaryKeySelective(RoleLevelSetting record);

    int updateByPrimaryKey(RoleLevelSetting record);
}
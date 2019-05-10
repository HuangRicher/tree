package com.tongzhu.welfare.mapper;

import com.tongzhu.welfare.model.BuildingTreeSetting;
import com.tongzhu.welfare.model.BuildingTreeSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingTreeSettingMapper {
    int countByExample(BuildingTreeSettingExample example);

    int deleteByExample(BuildingTreeSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BuildingTreeSetting record);

    int insertSelective(BuildingTreeSetting record);

    List<BuildingTreeSetting> selectByExample(BuildingTreeSettingExample example);

    BuildingTreeSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BuildingTreeSetting record, @Param("example") BuildingTreeSettingExample example);

    int updateByExample(@Param("record") BuildingTreeSetting record, @Param("example") BuildingTreeSettingExample example);

    int updateByPrimaryKeySelective(BuildingTreeSetting record);

    int updateByPrimaryKey(BuildingTreeSetting record);
}
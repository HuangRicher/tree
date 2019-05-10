package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseLevelSetting;
import com.tongzhu.treehouse.model.TreeHouseLevelSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHouseLevelSettingMapper {
    int countByExample(TreeHouseLevelSettingExample example);

    int deleteByExample(TreeHouseLevelSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TreeHouseLevelSetting record);

    int insertSelective(TreeHouseLevelSetting record);

    List<TreeHouseLevelSetting> selectByExample(TreeHouseLevelSettingExample example);

    TreeHouseLevelSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TreeHouseLevelSetting record, @Param("example") TreeHouseLevelSettingExample example);

    int updateByExample(@Param("record") TreeHouseLevelSetting record, @Param("example") TreeHouseLevelSettingExample example);

    int updateByPrimaryKeySelective(TreeHouseLevelSetting record);

    int updateByPrimaryKey(TreeHouseLevelSetting record);
}
package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseWorkSetting;
import com.tongzhu.treehouse.model.TreeHouseWorkSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHouseWorkSettingMapper {
    int countByExample(TreeHouseWorkSettingExample example);

    int deleteByExample(TreeHouseWorkSettingExample example);

    int deleteByPrimaryKey(Integer roleLevel);

    int insert(TreeHouseWorkSetting record);

    int insertSelective(TreeHouseWorkSetting record);

    List<TreeHouseWorkSetting> selectByExample(TreeHouseWorkSettingExample example);

    TreeHouseWorkSetting selectByPrimaryKey(Integer roleLevel);

    int updateByExampleSelective(@Param("record") TreeHouseWorkSetting record, @Param("example") TreeHouseWorkSettingExample example);

    int updateByExample(@Param("record") TreeHouseWorkSetting record, @Param("example") TreeHouseWorkSettingExample example);

    int updateByPrimaryKeySelective(TreeHouseWorkSetting record);

    int updateByPrimaryKey(TreeHouseWorkSetting record);
}
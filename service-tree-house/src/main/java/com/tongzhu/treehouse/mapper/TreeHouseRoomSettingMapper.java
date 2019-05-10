package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseRoomSetting;
import com.tongzhu.treehouse.model.TreeHouseRoomSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHouseRoomSettingMapper {
    int countByExample(TreeHouseRoomSettingExample example);

    int deleteByExample(TreeHouseRoomSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TreeHouseRoomSetting record);

    int insertSelective(TreeHouseRoomSetting record);

    List<TreeHouseRoomSetting> selectByExample(TreeHouseRoomSettingExample example);

    TreeHouseRoomSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TreeHouseRoomSetting record, @Param("example") TreeHouseRoomSettingExample example);

    int updateByExample(@Param("record") TreeHouseRoomSetting record, @Param("example") TreeHouseRoomSettingExample example);

    int updateByPrimaryKeySelective(TreeHouseRoomSetting record);

    int updateByPrimaryKey(TreeHouseRoomSetting record);
}
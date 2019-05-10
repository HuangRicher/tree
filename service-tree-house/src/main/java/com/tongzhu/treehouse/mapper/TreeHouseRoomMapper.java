package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseRoom;
import com.tongzhu.treehouse.model.TreeHouseRoomExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeHouseRoomMapper {
    int countByExample(TreeHouseRoomExample example);

    int deleteByExample(TreeHouseRoomExample example);

    int deleteByPrimaryKey(String id);

    int insert(TreeHouseRoom record);

    int insertSelective(TreeHouseRoom record);

    List<TreeHouseRoom> selectByExample(TreeHouseRoomExample example);

    TreeHouseRoom selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TreeHouseRoom record, @Param("example") TreeHouseRoomExample example);

    int updateByExample(@Param("record") TreeHouseRoom record, @Param("example") TreeHouseRoomExample example);

    int updateByPrimaryKeySelective(TreeHouseRoom record);

    int updateByPrimaryKey(TreeHouseRoom record);
}
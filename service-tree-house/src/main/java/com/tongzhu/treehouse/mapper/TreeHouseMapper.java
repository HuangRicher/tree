package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouse;
import com.tongzhu.treehouse.model.TreeHouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHouseMapper {
    int countByExample(TreeHouseExample example);

    int deleteByExample(TreeHouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(TreeHouse record);

    int insertSelective(TreeHouse record);

    List<TreeHouse> selectByExample(TreeHouseExample example);

    TreeHouse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TreeHouse record, @Param("example") TreeHouseExample example);

    int updateByExample(@Param("record") TreeHouse record, @Param("example") TreeHouseExample example);

    int updateByPrimaryKeySelective(TreeHouse record);

    int updateByPrimaryKey(TreeHouse record);
}
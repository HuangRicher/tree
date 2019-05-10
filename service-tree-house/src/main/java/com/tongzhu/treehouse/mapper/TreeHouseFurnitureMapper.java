package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseFurniture;
import com.tongzhu.treehouse.model.TreeHouseFurnitureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHouseFurnitureMapper {
    int countByExample(TreeHouseFurnitureExample example);

    int deleteByExample(TreeHouseFurnitureExample example);

    int deleteByPrimaryKey(String id);

    int insert(TreeHouseFurniture record);

    int insertSelective(TreeHouseFurniture record);

    List<TreeHouseFurniture> selectByExample(TreeHouseFurnitureExample example);

    TreeHouseFurniture selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TreeHouseFurniture record, @Param("example") TreeHouseFurnitureExample example);

    int updateByExample(@Param("record") TreeHouseFurniture record, @Param("example") TreeHouseFurnitureExample example);

    int updateByPrimaryKeySelective(TreeHouseFurniture record);

    int updateByPrimaryKey(TreeHouseFurniture record);
}
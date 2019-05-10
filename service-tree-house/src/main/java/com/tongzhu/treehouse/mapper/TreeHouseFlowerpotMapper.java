package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseFlowerpot;
import com.tongzhu.treehouse.model.TreeHouseFlowerpotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHouseFlowerpotMapper {
    int countByExample(TreeHouseFlowerpotExample example);

    int deleteByExample(TreeHouseFlowerpotExample example);

    int deleteByPrimaryKey(String id);

    int insert(TreeHouseFlowerpot record);

    int insertSelective(TreeHouseFlowerpot record);

    List<TreeHouseFlowerpot> selectByExample(TreeHouseFlowerpotExample example);

    TreeHouseFlowerpot selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TreeHouseFlowerpot record, @Param("example") TreeHouseFlowerpotExample example);

    int updateByExample(@Param("record") TreeHouseFlowerpot record, @Param("example") TreeHouseFlowerpotExample example);

    int updateByPrimaryKeySelective(TreeHouseFlowerpot record);

    int updateByPrimaryKey(TreeHouseFlowerpot record);
}
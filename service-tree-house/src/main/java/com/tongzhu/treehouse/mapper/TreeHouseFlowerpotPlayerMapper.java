package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseFlowerpotPlayer;
import com.tongzhu.treehouse.model.TreeHouseFlowerpotPlayerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHouseFlowerpotPlayerMapper {
    int countByExample(TreeHouseFlowerpotPlayerExample example);

    int deleteByExample(TreeHouseFlowerpotPlayerExample example);

    int insert(TreeHouseFlowerpotPlayer record);

    int insertSelective(TreeHouseFlowerpotPlayer record);

    List<TreeHouseFlowerpotPlayer> selectByExample(TreeHouseFlowerpotPlayerExample example);

    int updateByExampleSelective(@Param("record") TreeHouseFlowerpotPlayer record, @Param("example") TreeHouseFlowerpotPlayerExample example);

    int updateByExample(@Param("record") TreeHouseFlowerpotPlayer record, @Param("example") TreeHouseFlowerpotPlayerExample example);
}
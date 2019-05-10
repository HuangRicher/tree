package com.tongzhu.treehouse.mapper;

import com.tongzhu.treehouse.model.TreeHouseFlowerpotLog;
import com.tongzhu.treehouse.model.TreeHouseFlowerpotLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeHouseFlowerpotLogMapper {
    int countByExample(TreeHouseFlowerpotLogExample example);

    int deleteByExample(TreeHouseFlowerpotLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(TreeHouseFlowerpotLog record);

    int insertSelective(TreeHouseFlowerpotLog record);

    List<TreeHouseFlowerpotLog> selectByExample(TreeHouseFlowerpotLogExample example);

    TreeHouseFlowerpotLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TreeHouseFlowerpotLog record, @Param("example") TreeHouseFlowerpotLogExample example);

    int updateByExample(@Param("record") TreeHouseFlowerpotLog record, @Param("example") TreeHouseFlowerpotLogExample example);

    int updateByPrimaryKeySelective(TreeHouseFlowerpotLog record);

    int updateByPrimaryKey(TreeHouseFlowerpotLog record);
}
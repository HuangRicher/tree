package com.tongzhu.treehouse.mapper.ext;

import com.tongzhu.treehouse.model.TreeHouseFlowerpotLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeHouseFlowerpotLogExtMapper {

    List<TreeHouseFlowerpotLog> queryLogByTreeHouseUserId(@Param("treeHouseUserId") String treeHouseUserId);
}
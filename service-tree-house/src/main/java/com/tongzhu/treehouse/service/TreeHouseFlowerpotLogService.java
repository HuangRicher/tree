package com.tongzhu.treehouse.service;

import com.tongzhu.treehouse.model.TreeHouseFlowerpotLog;

import java.util.List;

public interface TreeHouseFlowerpotLogService {

    void add(Integer type,String operator,String treeHouseUserId,String message);

    List<TreeHouseFlowerpotLog> findByTreeHouseUserId(String treeHouseUserId);
}
